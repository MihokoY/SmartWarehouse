package s2.shipping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import s2.shipping.ShippingGrpc.ShippingImplBase;

public class ShippingServer extends ShippingImplBase {
	public static void main(String[] args) {
		
		// Create an instance of this class
		ShippingServer shippingserver = new ShippingServer();
		// Get properties
		Properties prop = shippingserver.getProperties();
		// Register jmDNS service
		shippingserver.registerService(prop);
		// Get port number // #.50052;
		int port = Integer.valueOf( prop.getProperty("service2_port") );
		
		Server server;
		try {
			// Create a server on the port
			server = ServerBuilder.forPort(port).addService(shippingserver).build().start();
			System.out.println("Shipping Server started, listening on " + port);
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
			System.out.println("Shipping Service properies ...");
			System.out.println("\t service2_type: " + prop.getProperty("service2_type"));
			System.out.println("\t service2_name: " +prop.getProperty("service2_name"));
			System.out.println("\t service2_description: " +prop.getProperty("service2_description"));
			System.out.println("\t service2_port: " +prop.getProperty("service2_port"));

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

			String service_type = prop.getProperty("service2_type");// "_shipping._tcp.local.";
			String service_name = prop.getProperty("service2_name");// "shipping";
			int service_port = Integer.valueOf(prop.getProperty("service2_port"));// #.50052;

			String service_description_properties = prop.getProperty("service2_description");// "service for shipping management";

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
	 * Method1 checkShippingQuantity
	 */
	@Override
	public StreamObserver<ShippingQtyRequest> checkShippingQuantity(
			StreamObserver<ShippingQtyResponse> responseObserver) {
		
		return new StreamObserver<ShippingQtyRequest>() {

			// Prepare ArrayList for received data
			ArrayList<String> list = new ArrayList();
			
			// Set the default response value
			String message = "";

			@Override
			public void onNext(ShippingQtyRequest request) {

				System.out.println("Receiving shipping product: " + request.getProductNo() + ", quantity: " + request.getShippingQty() );

				// Add received request from the client into the list
				list.add(request.getProductNo() + "," + request.getShippingQty());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stubal
			}

			@Override
			public void onCompleted() {
				System.out.printf("Receiving checkShippingQuantity method complete. \n" );
								
				// Read the csv file
				BufferedReader br = null;
				
				try{
					br = new BufferedReader(new FileReader("src/main/java/ShippingList.csv"));
					
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
						message = "The shipping quantity is correct.";
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
				ShippingQtyResponse reply = ShippingQtyResponse.newBuilder().setMessage(message).build();
				responseObserver.onNext(reply);

				// Completed
				responseObserver.onCompleted();
			}
		};
	}

	
	/**
	 * Method2 updateLocation
	 */
	@Override
	public StreamObserver<UpdateLocRequest> updateLocation(StreamObserver<UpdateLocResponse> responseObserver) {
		return new StreamObserver<UpdateLocRequest> () {
			@Override
			public void onNext(UpdateLocRequest ulr) {
				
				// Get the request from the client
				String indivNo = ulr.getProductIndivNo();
				System.out.println("Receiving updateLocation method productIndivNo: "+ indivNo);
				
				// Set the default response value
				String locatNo =  "";
				
				// Read the csv file
				BufferedReader br = null;
				BufferedWriter bw = null;
				try{
					br = new BufferedReader(new FileReader("src/main/java/LocationList.csv"));
					bw = new BufferedWriter(new FileWriter("src/main/java/tmp_LocationList.csv"));
					String line="";
					String[] tempArr; // Use this to store each column in a line

					// Read first line(header) and add into the temporary file 
					String title = br.readLine(); 
					bw.write(title);
					bw.newLine();
					bw.flush();

					while((line = br.readLine()) != null){ // Read each line of file
						tempArr = line.split(","); // Split columns by comma

						// First column
						String productIndivNo = tempArr[0];
						// Second column
						String locationNo = tempArr[1];

						// Get the location No where productNo is the same
						if(indivNo.equals(productIndivNo)) {
							// Set the locationNo
							locatNo = locationNo;
							
							// Do not add this line into the temporary file to delete

						}else {
							// Add this line into the temporary file
							bw.write(line);
							bw.newLine();
							bw.flush();
						}
					}
							
					// Set the response value
					UpdateLocResponse reply = UpdateLocResponse.newBuilder().setLocationNo(locatNo).build();				
					responseObserver.onNext(reply);
				
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
				
				// Update LocationList.csv (copy from the temporary file)
				Path original  = Paths.get("src/main/java/LocationList.csv");
				Path temp  = Paths.get("src/main/java/tmp_LocationList.csv");
				try {
					Files.copy(temp, original, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
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
}
