package s1.receiving;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import s1.receiving.ReceivingGrpc.ReceivingImplBase;

public class ReceivingServer extends ReceivingImplBase {
	public static void main(String[] args) {
		ReceivingServer receivingserver = new ReceivingServer();
		
		Properties prop = receivingserver.getProperties();
		
		receivingserver.registerService(prop);
		
		int port = Integer.valueOf( prop.getProperty("service1_port") );// #.50051;
		
		try {
			Server server = ServerBuilder.forPort(port).addService(receivingserver).build().start();
			System.out.println("Receiving Server started, listening on " + port);
			server.awaitTermination();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}				
	}
	
	
	/**
	 * Get properties
	 */ 
	private Properties getProperties() {

		Properties prop = null;

		try (InputStream input = new FileInputStream("src/main/resources/warehouse.properties")) {
			prop = new Properties();
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Receiving Service properies ...");
			System.out.println("\t service1_type: " + prop.getProperty("service1_type"));
			System.out.println("\t service1_name: " +prop.getProperty("service1_name"));
			System.out.println("\t service1_description: " +prop.getProperty("service1_description"));
			System.out.println("\t service1_port: " +prop.getProperty("service1_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}
	
	
	/**
	 * Register jmDNS service
	 */
	private void registerService(Properties prop) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			String service_type = prop.getProperty("service1_type");// "_receiving._tcp.local.";
			String service_name = prop.getProperty("service1_name");// "receiving";
			int service_port = Integer.valueOf(prop.getProperty("service1_port"));// #.50051;

			String service_description_properties = prop.getProperty("service1_description");// "service for receiving management";

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
			
			// Wait for a bit
			Thread.sleep(1000);

			// Unregister all services
			// jmdns.unregisterAllServices();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	
	/**
	 * Method1 checkReceivedQuantity
	 */
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

				// set the response value
				ReceivedQtyResponse reply = ReceivedQtyResponse.newBuilder().setMessage(message).build();
				responseObserver.onNext(reply);

				//completed
				responseObserver.onCompleted();

			}
		};
	}
	
	
	/**
	 * Method2 setLocation
	 */
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
					bw = new BufferedWriter(new FileWriter("src/main/java/LocationList.csv",true));
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
							// set the locationNo
							locatNo = locationNo;
							
							// update LocationList.csv (add this product data)
							String outputLine = String.join(",",indivNo,locatNo);
							bw.write(outputLine);
							bw.newLine();
							bw.flush();
							break;
						}
					}
				} catch (IOException e) {
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
				
				// set the response value
				SetLocResponse reply = SetLocResponse.newBuilder().setLocationNo(locatNo).build();				
				responseObserver.onNext(reply);
			}

			@Override
			public void onError(Throwable t) {				
				t.printStackTrace();				
			}

			@Override
			public void onCompleted() {
				System.out.println("receiving setLocation method completed. ");
				
				//completed
				responseObserver.onCompleted();
			}			
		};
	}

		
	/**
	 * Method3 checkLocationAvailability
	 */
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
		
		// set the response value
		LocationAvailResponse reply = LocationAvailResponse.newBuilder().setAvailNum(availNum).build();
		System.out.println("Reply availability: " + availNum);
		responseObserver.onNext(reply);
		
		//completed
		responseObserver.onCompleted();		
	}
}