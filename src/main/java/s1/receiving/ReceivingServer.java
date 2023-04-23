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

import com.google.rpc.BadRequest;
import com.google.rpc.BadRequest.FieldViolation;

import io.grpc.Metadata;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;
import s1.receiving.ReceivingGrpc.ReceivingImplBase;

public class ReceivingServer extends ReceivingImplBase {
	public static void main(String[] args) {
		
		// Create an instance of this class
		ReceivingServer receivingserver = new ReceivingServer();
		// Get properties
		Properties prop = receivingserver.getProperties();
		// Register jmDNS service
		receivingserver.registerService(prop);
		// Get port number // #.50051;
		int port = Integer.valueOf( prop.getProperty("service1_port") );
		
		Server server;
		try {
			// Create a server on the port
			server = ServerBuilder.forPort(port).addService(receivingserver).build().start();
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

			System.out.printf("Registrering service with type %s and name %s \n", service_type, service_name);
			
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

			// Prepare ArrayList for received data
			ArrayList<String> list = new ArrayList();
			
			// Set the default response value
			String message = "";

			@Override
			public void onNext(ReceivedQtyRequest request) {

				System.out.println("Receiving received product: " + request.getProductNo() + ", quantity: " + request.getReceivedQty() );

				// Add received request from the client into the list
				list.add(request.getProductNo() + "," + request.getReceivedQty());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stubal
			}

			@Override
			public void onCompleted() {
				System.out.printf("Receiving checkReceivedQuantity method complete. \n" );
								
				// Read the csv file
				BufferedReader br = null;
				
				try{
					br = new BufferedReader(new FileReader("src/main/java/ReceivedList.csv"));
					
					String line="";
					String[] tempArr; // Use this to store each column in a line

					br.readLine(); // Read first line(header)

					while((line = br.readLine()) != null){ // Read each line of file
						tempArr = line.split(","); // Split columns by comma

						// First column
						String ProductNo = tempArr[0];
						// Second column
						int Quantity = Integer.parseInt(tempArr[1]);

						// Compare to the list of the server side
						for(int i=0; i<list.size(); i++) {
							// Split the list data to productNo and quantity
							String[] temp = list.get(i).split(",");
							
							// Compare the quantity where the productNos are the same
							if(temp[0].equals(ProductNo)) {
								if(Integer.parseInt(temp[1]) != Quantity) {
									// If the quantity is different, add the productNo into the message
									message = message.concat(ProductNo + " ");
								}
							}
						}	
					}
					
					// Set the message
					if(message.equals("")) {
						// If there is no difference
						message = "The received quantity is correct.";
					}else {
						// If there is difference
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

				// Set the response value
				ReceivedQtyResponse reply = ReceivedQtyResponse.newBuilder().setMessage(message).build();
				responseObserver.onNext(reply);

				// Completed
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
				
				// Get the requests from the client
				String prdctNo = slr.getProductNo();
				String indivNo = slr.getProductIndivNo();
				System.out.println("Receiving setLocation method productNo: "+ prdctNo + " individualNo: "+ indivNo);
				
				// Set the default response value
				String locatNo =  "";
				
				// Read the csv file
				BufferedReader br = null;
				BufferedWriter bw = null;
				
				try{
					br = new BufferedReader(new FileReader("src/main/java/LocationAvailability.csv"));					
					bw = new BufferedWriter(new FileWriter("src/main/java/LocationList.csv",true));
					String line="";
					String[] tempArr; // Use this to store each column in a line

					br.readLine(); // Read first line(header)

					while((line = br.readLine()) != null){ // Read each line of file
						tempArr = line.split(","); // Split columns by comma

						// First column
						String locationNo = tempArr[0];
						// Second column
						int availableNum = Integer.parseInt(tempArr[1]);
						// Third column
						String ProductNo = tempArr[2];

						// Get the location No where productNo is the same and available number is greater than 0
						if(ProductNo.equals(prdctNo) && availableNum > 0) {
							// Set the locationNo
							locatNo = locationNo;
							
							// Update LocationList.csv (add this product data)
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
				
				// Set the response value
				SetLocResponse reply = SetLocResponse.newBuilder().setLocationNo(locatNo).build();				
				responseObserver.onNext(reply);
			}

			@Override
			public void onError(Throwable t) {				
				t.printStackTrace();				
			}

			@Override
			public void onCompleted() {
				System.out.println("Receiving setLocation method completed. ");
				
				// Completed
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
		
		// Get the request from the client
		String locatNo = request.getLocationNo();
		
		// Remote Error Handling
		//if(locatNo == null) {
		//	FieldViolation inputError = BadRequest.FieldViolation.newBuilder()
		//								.setField("locateNo").setDescription("locateNo is null").build(); 
		//	BadRequest badRequestError = BadRequest.newBuilder().addFieldViolations(inputError).build();
		//	Metadata errorDetail = new Metadata();
		//	errorDetail.put(ProtoUtils.keyForProto(badRequestError), badRequestError);
	    //  responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT.withDescription("locateNo is null")
	  	//          .asRuntimeException(errorDetail));
		//}
		
		System.out.println("Received location No: " + locatNo);
		
		// Set the default response value
		int availNum = 0;
		
		// Read the csv file
		BufferedReader br = null;
		
		try{
			br = new BufferedReader(new FileReader("src/main/java/LocationAvailability.csv"));
			String line="";
			String[] tempArr; // Use this to store each column in a line

			br.readLine(); // Read first line(header)

			while((line = br.readLine()) != null){ // Read each line of file
				tempArr = line.split(","); // Split columns by comma

				// First column
				String locationNo = tempArr[0];
				// Second column
				int availableNum = Integer.parseInt(tempArr[1]);

				// Get the available number where location No is the same
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
		
		// Set the response value
		LocationAvailResponse reply = LocationAvailResponse.newBuilder().setAvailNum(availNum).build();
		System.out.println("Reply availability: " + availNum);
		responseObserver.onNext(reply);
		
		// Completed
		responseObserver.onCompleted();		
	}
}