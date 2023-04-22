package s3.inventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import s3.inventory.InventoryGrpc.InventoryImplBase;

public class InventoryServer extends InventoryImplBase {
	public static void main(String[] args) {
		
		// Create an instance of this class
		InventoryServer inventoryserver = new InventoryServer();
		// Get properties
		Properties prop = inventoryserver.getProperties();
		// Register jmDNS service
		inventoryserver.registerService(prop);
		// Get port number // #.50053;
		int port = Integer.valueOf( prop.getProperty("service3_port") );
		
		Server server;
		try {
			// Create a server on the port
			server = ServerBuilder.forPort(port).addService(inventoryserver).build().start();
			System.out.println("Inventory Server started, listening on " + port);
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
			System.out.println("Inventory Service properies ...");
			System.out.println("\t service3_type: " + prop.getProperty("service3_type"));
			System.out.println("\t service3_name: " +prop.getProperty("service3_name"));
			System.out.println("\t service3_description: " +prop.getProperty("service3_description"));
			System.out.println("\t service3_port: " +prop.getProperty("service3_port"));

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

			String service_type = prop.getProperty("service3_type");// "_inventory._tcp.local.";
			String service_name = prop.getProperty("service3_name");// "inventory";
			int service_port = Integer.valueOf(prop.getProperty("service3_port"));// #.50053;

			String service_description_properties = prop.getProperty("service3_description");// "service for inventory management";

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
	 * Method1 checkInventoryQuantity
	 */
	@Override
	public void checkInventoryQuantity(InventoryQtyRequest request,
			StreamObserver<InventoryQtyResponse> responseObserver) {
		
		System.out.println("Receiving checkInventoryQuantity method totalQty: " + request.getTotalQty() );
		
		// Get the request from the client
		int totalQty = request.getTotalQty();
		
		// Prepare ArrayList to stock productNos
		ArrayList<String> productNos = new ArrayList();
		
		// Read the csv file
		BufferedReader br = null;
		
		try{
			br = new BufferedReader(new FileReader("src/main/java/InventoryList.csv"));
			String line="";
			String[] tempArr; // Use this to store each column in a line

			br.readLine(); // Read first line(header)

			while((line = br.readLine()) != null){ // Read each line of file
				tempArr = line.split(","); // Split columns by comma

				// First column
				String ProductNo = tempArr[0];
				// Second column
				int TotalQty = Integer.parseInt(tempArr[1]);
					
				// If total quantity is less than totalQty, get the product No
				if(TotalQty < totalQty) {
					productNos.add(ProductNo);
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
		
		// Response all productNos
		for(int i=0; i<productNos.size(); i++) {

			// Set the response value
			InventoryQtyResponse reply = InventoryQtyResponse.newBuilder().setProductNo(productNos.get(i)).build();
			responseObserver.onNext(reply);

			try {
				// Wait for a second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Completed
		responseObserver.onCompleted();
	}

	
	/**
	 * Method2 order
	 */
	@Override
	public StreamObserver<OrderRequest> order(StreamObserver<OrderResponse> responseObserver) {
		
		return new StreamObserver<OrderRequest> () {

			@Override
			public void onNext(OrderRequest or) {
				
				// Get the request from the client
				String prdctNo = or.getProductNo();
				int orderQty = or.getOrderQty();
				System.out.println("Receiving order method productNo: " + prdctNo + " orderQty: " + orderQty);
				
				// Set the default response value
				int afterQty = 0;
				
				// Read the csv file
				BufferedReader br = null;
				BufferedWriter bw = null;
				
				try{
					br = new BufferedReader(new FileReader("src/main/java/InventoryList.csv"));
					bw = new BufferedWriter(new FileWriter("src/main/java/OrderList.csv",true));
					String line="";
					String[] tempArr; // Use this to store each column in a line

					br.readLine(); // Read first line(header)

					while((line = br.readLine()) != null){ // Read each line of file
						tempArr = line.split(","); // Split columns by comma

						// First column
						String ProductNo = tempArr[0];
						// Second column
						int TotalQty = Integer.parseInt(tempArr[1]);

						// Get the afterQty where productNo is the same
						if(ProductNo.equals(prdctNo)) {
							// Set the afterQty
							afterQty = TotalQty + orderQty;
							
							// Update OrderList.csv (add this order data)
							SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy",Locale.UK);							
							Date today = new Date();;
							String outputLine = String.join(",",ft.format(today),ProductNo,Integer.toString(orderQty));
							bw.write(outputLine);
							bw.newLine();
							bw.flush();
							break;
						}
					}			
				
				// Set the response value
				OrderResponse reply = OrderResponse.newBuilder().setProductNo(prdctNo).setOrderQty(orderQty).setAfterQty(afterQty).build();				
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
			}

			@Override
			public void onError(Throwable t) {				
				t.printStackTrace();				
			}

			@Override
			public void onCompleted() {
				System.out.println("Receiving order method completed. ");
				
				// Completed
				responseObserver.onCompleted();
			}		
		};
	}


	/**
	 * Method3 orderHistory
	 */
	@Override
	public void orderHistory(OrderHisRequest request, StreamObserver<OrderHisResponse> responseObserver) {
		
		System.out.println("Receiving orderHistory method startDate: " + request.getStartDate() 
							+ ", endDate: " + request.getEndDate()+ ", productNo: " + request.getProductNo() );
		
		// Set the date format
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy",Locale.UK);
		Date startDate = null;
		Date endDate = null;
		
		try {
			// Get the requests from the client and change to the format
			startDate = ft.parse(request.getStartDate());
			endDate = ft.parse(request.getEndDate());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// Get the requests from the client
		String productNo = request.getProductNo();
		
		// Set the default response value
		int totalQty = 0;
		
		// Read the csv file
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("src/main/java/OrderList.csv"));
			String line="";
			String[] tempArr; // Use this to store each column in a line

			br.readLine(); // Read first line(header)			
						
			while((line = br.readLine()) != null){ // Read each line of file
				tempArr = line.split(","); // Split columns by comma

				// First column
				Date date = ft.parse(tempArr[0]);
				// Second column
				String prdctNo = tempArr[1];
				// Third column
				int orderQty = Integer.parseInt(tempArr[2]);
					
				// Add totalQty and totalPrice where productNo and date match
				if(productNo.equals(prdctNo) && date.compareTo(startDate)>=0 && date.compareTo(endDate)<=0) {					
					totalQty = totalQty + orderQty;
				}				
			}		
			
		} catch (IOException | ParseException e) {
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
		OrderHisResponse reply = OrderHisResponse.newBuilder().setTotalQty(totalQty).build();
		System.out.println("Reply totalQty: " + totalQty);
		responseObserver.onNext(reply);
		
		// Completed
		responseObserver.onCompleted();
	}
}
