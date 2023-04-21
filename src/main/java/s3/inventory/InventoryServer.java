package s3.inventory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
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
		InventoryServer inventoryserver = new InventoryServer();
		
		Properties prop = inventoryserver.getProperties();
		
		inventoryserver.registerService(prop);
		
		int port = Integer.valueOf( prop.getProperty("service3_port") );// #.50053;
		
		Server server;
		try {
			server = ServerBuilder.forPort(port).addService(inventoryserver).build().start();
			System.out.println("Inventory Server started, listening on " + port);
			server.awaitTermination();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
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

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
			
			// Wait for a bit
			Thread.sleep(1000);

			// Unregister all services
			// jmdns.unregisterAllServices();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void checkInventoryQuantity(InventoryQtyRequest request,
			StreamObserver<InventoryQtyResponse> responseObserver) {
		
		System.out.println("receiving checkInventoryQuantity method totalQty: " + request.getTotalQty() );
		
		int totalQty = request.getTotalQty();
		
		ArrayList<String> productNos = new ArrayList();
		
		//import the csv file
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("src/main/java/InventoryList.csv"));
			String line="";
			String[] tempArr; // using this to store each column in a line

			br.readLine(); // reading first line to avoid the header

			while((line = br.readLine()) != null){ // reading each line of file
				tempArr = line.split(","); // each column has a comma between it

				// first column
				String ProductNo = tempArr[0];
				// second column
				int TotalQty = Integer.parseInt(tempArr[1]);
					
				// if total quantity is less than totalQty, get the product No
				if(TotalQty < totalQty) {
					productNos.add(ProductNo);
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
		
		for(int i=0; i<productNos.size(); i++) {

			InventoryQtyResponse reply = InventoryQtyResponse.newBuilder().setProductNo(productNos.get(i)).build();

			responseObserver.onNext(reply);

			try {
				//wait for a second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		responseObserver.onCompleted();
	}

	
	
	@Override
	public StreamObserver<OrderRequest> order(StreamObserver<OrderResponse> responseObserver) {
		
		return new StreamObserver<OrderRequest> () {

			@Override
			public void onNext(OrderRequest or) {
				
				String prdctNo = or.getProductNo();
				int orderQty = or.getOrderQty();
				System.out.println("receiving order method productNo: " + prdctNo + " orderQty: " + orderQty);
				
				// set the default response value(locatNo)
				int afterQty = 0;
				
				// read the csv file
				BufferedReader br = null;
				try{
					br = new BufferedReader(new FileReader("src/main/java/InventoryList.csv"));
					String line="";
					String[] tempArr; // using this to store each column in a line

					br.readLine(); // reading first line to avoid the header

					while((line = br.readLine()) != null){ // reading each line of file
						tempArr = line.split(","); // each column has a comma between it

						// first column
						String ProductNo = tempArr[0];
						// second column
						int TotalQty = Integer.parseInt(tempArr[1]);

						// set the afterQty where productNos are the same
						if(ProductNo.equals(prdctNo)) {
							afterQty = TotalQty + orderQty;
							// update OrderList.csv (add this order data)
							break;
						}
					}			
				
				OrderResponse reply = OrderResponse.newBuilder().setProductNo(prdctNo).setOrderQty(orderQty).setAfterQty(afterQty).build();
				
				responseObserver.onNext(reply);
				
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
			}

			@Override
			public void onError(Throwable t) {				
				t.printStackTrace();				
			}

			@Override
			public void onCompleted() {
				System.out.println("receiving order method completed. ");
				
				//completed too
				responseObserver.onCompleted();
			}
			
		};
	}

	

	@Override
	public void orderHistory(OrderHisRequest request, StreamObserver<OrderHisResponse> responseObserver) {
		
		System.out.println("receiving orderHistory method startDate: " + request.getStartDate() 
							+ ", endDate: " + request.getEndDate()+ ", productNo: " + request.getProductNo() );
		
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy",Locale.UK);
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = ft.parse(request.getStartDate());
			endDate = ft.parse(request.getEndDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String productNo = request.getProductNo();
		
		// set the default response value(availNum)
		int totalQty = 0;
		float totalPrice = 0;
		
		//import the csv file
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("src/main/java/OrderList.csv"));
			String line="";
			String[] tempArr; // using this to store each column in a line

			br.readLine(); // reading first line to avoid the header			
						
			while((line = br.readLine()) != null){ // reading each line of file
				tempArr = line.split(","); // each column has a comma between it

				// first column
				Date date = ft.parse(tempArr[0]);
				// second column
				String prdctNo = tempArr[1];
				// Third column
				int orderQty = Integer.parseInt(tempArr[2]);
				// Fourth column
				float totalPrc = Float.parseFloat(tempArr[3]);
					
				// add totalQty and totalPrice where productNo and date are match
				if(productNo.equals(prdctNo) && date.compareTo(startDate)>=0 && date.compareTo(endDate)<=0) {					
					totalQty = totalQty + orderQty;
					totalPrice = totalPrice + totalPrc;
				}				
			}		
			
		} catch (IOException | ParseException e) {
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

		OrderHisResponse reply = OrderHisResponse.newBuilder().setTotalQty(totalQty).setTotalPrice(totalPrice).build();
		System.out.println("Reply totalQty: " + totalQty + ", Reply totalQty: " + totalPrice);
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}
