package s3.inventory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import s1.receiving.SetLocRequest;
import s1.receiving.SetLocResponse;
import s3.inventory.InventoryGrpc.InventoryImplBase;

public class InventoryServer extends InventoryImplBase {
	public static void main(String[] args) {
		InventoryServer inventoryserver = new InventoryServer();
		int port = 50053;
		
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
							// update OrderHistory.csv (add this order data)
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

	
	/*
	@Override
	public void orderHistory(OrderHisRequest request, StreamObserver<OrderHisResponse> responseObserver) {
		
		System.out.println("receiving orderHistory method startDate: " + request.getStartDate() + ", endDate: " + request.getEndDate() );
		
		String startDate = request.getStartDate();
		String endDate = request.getEndDate();
		
		ArrayList<String> history = new ArrayList();
		
		//import the csv file
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("src/main/java/OrderList.csv"));
			String line="";
			String[] tempArr; // using this to store each column in a line

			br.readLine(); // reading first line to avoid the header

			// this is used to format the incoming date
			SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
						
			while((line = br.readLine()) != null){ // reading each line of file
				tempArr = line.split(","); // each column has a comma between it

				// first column
				Date date = (Date) ft.parse(tempArr[0]);
				// second column
				String prdctNo = tempArr[1];
				// Third column
				int orderQty = Integer.parseInt(tempArr[2]);
				// Fourth column
				float totalPrice = Integer.parseInt(tempArr[3]);
					
				// 
				if() {

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
		
		for(int i=0; i<history.size(); i++) {

			OrderHisResponse reply = OrderHisResponse.newBuilder().setProductNo(history).setTotalQty(totalQty).setTotalPrice(totalPrice).build();

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
	*/
}
