package s2.shipping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import s2.shipping.ShippingGrpc.ShippingImplBase;

public class ShippingServer extends ShippingImplBase {
	public static void main(String[] args) {
		ShippingServer shippingserver = new ShippingServer();
		int port = 50052;
		
		Server server;
		try {
			server = ServerBuilder.forPort(port).addService(shippingserver).build().start();
			System.out.println("Shipping Server started, listening on " + port);
			server.awaitTermination();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public StreamObserver<ShippingQtyRequest> checkShippingQuantity(
			StreamObserver<ShippingQtyResponse> responseObserver) {
		
		return new StreamObserver<ShippingQtyRequest>() {

			// prepare ArrayList for received data
			ArrayList<String> list = new ArrayList();
			
			// set the default reply message
			String message = "";

			@Override
			public void onNext(ShippingQtyRequest request) {

				System.out.println("receiving shipping product: " + request.getProductNo() + ", quantity: " + request.getShippingQty() );

				// add received data into the list
				list.add(request.getProductNo() + "," + request.getShippingQty());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stubal
			}

			@Override
			public void onCompleted() {
				System.out.printf("receiving checkShippingQuantity method complete. \n" );
								
				//import the csv file
				BufferedReader br = null;
				try{
					br = new BufferedReader(new FileReader("src/main/java/ShippingList.csv"));
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
						message = "The shipping quantity is correct.";
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

				ShippingQtyResponse reply = ShippingQtyResponse.newBuilder().setMessage(message).build();

				responseObserver.onNext(reply);

				responseObserver.onCompleted();

			}

		};
	}

	@Override
	public StreamObserver<UpdateLocRequest> updateLocation(StreamObserver<UpdateLocResponse> responseObserver) {
		return new StreamObserver<UpdateLocRequest> () {
			@Override
			public void onNext(UpdateLocRequest ulr) {
				
				String indivNo = ulr.getProductIndivNo();
				System.out.println("receiving updateLocation method productIndivNo: "+ indivNo);
				
				// set the default response value(locatNo)
				String locatNo =  "";
				// set the default response value(availNum)
				int availNum =  0;
				
				// read the csv file
				BufferedReader br = null;
				try{
					br = new BufferedReader(new FileReader("src/main/java/LocationList.csv"));
					String line="";
					String[] tempArr; // using this to store each column in a line

					br.readLine(); // reading first line to avoid the header

					while((line = br.readLine()) != null){ // reading each line of file
						tempArr = line.split(","); // each column has a comma between it

						// first column
						String productIndivNo = tempArr[0];
						// second column
						String locationNo = tempArr[1];

						// get the location No where productNos are the same
						if(indivNo.equals(productIndivNo)) {
							locatNo = locationNo;
							// update LocationAvailability.csv (availableNum -= 1)
							// update LocationList.csv (delete this product data)
							// update InventoryList.csv (totalQty -= 1)
							break;
						}
					}
					
					// get available number
					br = new BufferedReader(new FileReader("src/main/java/LocationAvailability.csv"));
					String line2="";
					String[] tempArr2; // using this to store each column in a line

					br.readLine(); // reading first line to avoid the header

					while((line2 = br.readLine()) != null){ // reading each line of file
						tempArr2 = line2.split(","); // each column has a comma between it

						// first column
						String locationNo = tempArr2[0];
						// second column
						int availableNum = Integer.parseInt(tempArr2[1]);
						// third column
						String ProductNo = tempArr2[2];

						// get the available number where locationNos are the same
						if(locatNo.equals(locationNo)) {
							// reduce availableNum
							availNum = availableNum - 1;
							// update LocationAvailability.csv (availableNum += 1)
							break;
						}
					}
								
					UpdateLocResponse reply = UpdateLocResponse.newBuilder().setLocationNo(locatNo).setAvailNum(availNum).build();
				
				responseObserver.onNext(reply);
				
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
				}
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
}
