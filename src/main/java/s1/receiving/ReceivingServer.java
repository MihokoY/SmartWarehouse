package s1.receiving;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import s1.receiving.ReceivingGrpc.ReceivingImplBase;

public class ReceivingServer extends ReceivingImplBase {
	public static void main(String[] args) {
		ReceivingServer receivingserver = new ReceivingServer();
		int port = 50051;
		
		Server server;
		try {
			server = ServerBuilder.forPort(port).addService(receivingserver).build().start();
			System.out.println("Receiving Server started, listening on " + port);
			server.awaitTermination();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public StreamObserver<ReceivedQtyRequest> checkReceivedQuantity(
			StreamObserver<ReceivedQtyResponse> responseObserver) {
		
		return new StreamObserver<ReceivedQtyRequest>() {

			// prepare ArrayList for received data
			ArrayList<String> list = new ArrayList();
			
			// set the default reply message
			String message = "";

			@Override
			public void onNext(ReceivedQtyRequest request) {

				System.out.println("receiving received product: " + request.getProductNo() + ", quantity: " + request.getReceivedQty() );

				// add received data into the list
				list.add(request.getProductNo() + "," + request.getReceivedQty());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stubal
			}

			@Override
			public void onCompleted() {
				System.out.printf("receiving checkReceivedQuantity method complete. \n" );
								
				//import the csv file
				BufferedReader br = null;
				
				try{
					br = new BufferedReader(new FileReader("src/main/java/ReceivedList.csv"));
					
					String line="";
					String[] tempArr; // using this to store each column in a line

					br.readLine(); // reading first line to avoid the header

					while((line = br.readLine()) != null){ // reading each line of file
						tempArr = line.split(","); // each column has a comma between it

						// first column
						String ProductNo = tempArr[0];
						// second column
						int Quantity = Integer.parseInt(tempArr[1]);

						//compare to the list of the server side
						for(int i=0; i<list.size(); i++) {
							// split the list data to productNo and quantity
							String[] temp = list.get(i).split(",");
							
							// compare the quantity where the productNos are the same
							if(temp[0].equals(ProductNo)) {
								if(Integer.parseInt(temp[1]) != Quantity) {
									//if the quantity is different, add the productNo into the message
									message = message.concat(ProductNo + " ");
								}
							}
						}	
					}
					
					// set the message
					if(message.equals("")) {
						//if there is no difference
						message = "The received quantity is correct.";
					}else {
						//if there is difference
						message = "The incorrect product number: " + message;
					}
				
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					if(br!=null){
						try {
							br.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}					

				ReceivedQtyResponse reply = ReceivedQtyResponse.newBuilder().setMessage(message).build();

				responseObserver.onNext(reply);

				responseObserver.onCompleted();

			}

		};
	}

	@Override
	public StreamObserver<SetLocRequest> setLocation(StreamObserver<SetLocResponse> responseObserver) {
		return new StreamObserver<SetLocRequest> () {

			@Override
			public void onNext(SetLocRequest slr) {
				
				String prdctNo = slr.getProductNo();
				String indivNo = slr.getProductIndivNo();
				System.out.println("receiving setLocation method productNo: "+ prdctNo + " individualNo: "+ indivNo);
				
				// set the default response value(locatNo)
				String locatNo =  "";
				
				// read the csv file
				BufferedReader br = null;
				BufferedWriter bw = null;
				
				try{
					br = new BufferedReader(new FileReader("src/main/java/LocationAvailability.csv"));
					bw = new BufferedWriter(new FileWriter("src/main/java/LocationAvailability.csv",true));
					String line="";
					String[] tempArr; // using this to store each column in a line

					br.readLine(); // reading first line to avoid the header

					while((line = br.readLine()) != null){ // reading each line of file
						tempArr = line.split(","); // each column has a comma between it

						// first column
						String locationNo = tempArr[0];
						// second column
						int availableNum = Integer.parseInt(tempArr[1]);
						// third column
						String ProductNo = tempArr[2];

						// get the location No where productNos are the same and available number is greater than 0
						if(ProductNo.equals(prdctNo) && availableNum > 0) {
							locatNo = locationNo;
							// update LocationAvailability.csv (availableNum += 1)
							// update LocationList.csv (add this product data)
							// update InventoryList.csv (totalQty += 1)
							String outputLine = String.join(",",locationNo,String.valueOf(availableNum-1),ProductNo);
							//line = line.replace(String.valueOf(availableNum), String.valueOf(availableNum-1));
							bw.write(outputLine);
							System.out.println(outputLine);
		                    bw.newLine();
		                    bw.flush();
							break;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					if(br!=null){
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(bw!=null){
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
				SetLocResponse reply = SetLocResponse.newBuilder().setProductIndivNo(indivNo).setLocationNo(locatNo).build();
				
				responseObserver.onNext(reply);
			}

			@Override
			public void onError(Throwable t) {
				
				t.printStackTrace();				
			}

			@Override
			public void onCompleted() {
				System.out.println("receiving setLocation method completed. ");
				
				//completed too
				responseObserver.onCompleted();
			}
			
		};
	}

	@Override
	public void checkLocationAvailability(LocationAvailRequest request,
			StreamObserver<LocationAvailResponse> responseObserver) {
		
		// get the request(locationNo) from the client
		String locatNo = request.getLocationNo();
		System.out.println("Received location No: " + locatNo);
		
		// set the default response value(availNum)
		int availNum = 0;
		
		// read the csv file
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("src/main/java/LocationAvailability.csv"));
			String line="";
			String[] tempArr; // using this to store each column in a line

			br.readLine(); // reading first line to avoid the header

			while((line = br.readLine()) != null){ // reading each line of file
				tempArr = line.split(","); // each column has a comma between it

				// first column
				String locationNo = tempArr[0];
				// second column
				int availableNum = Integer.parseInt(tempArr[1]);

				// get the available number where location No is same
				if(locationNo.equals(locatNo)) {
					availNum = availableNum;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// set the response value
		LocationAvailResponse reply = LocationAvailResponse.newBuilder().setAvailNum(availNum).build();
		System.out.println("Reply availability: " + availNum);
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
		
	}
}