package s3.inventory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import s3.inventory.InventoryGrpc.InventoryBlockingStub;
import s3.inventory.InventoryGrpc.InventoryStub;

public class InventoryClient {
	private static InventoryBlockingStub blockingStub;
	private static InventoryStub asyncStub;
	
	private static ServiceInfo InventoryInfo;
	
	public static void main(String[] args) {
				
		String inventory_service_type = "_inventory._tcp.local.";
		discoverInventoryService(inventory_service_type);
		
		String host = InventoryInfo.getHostAddresses()[0];
		int port = InventoryInfo.getPort();
		//String host = "localhost";
		//int port = 50053;
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		blockingStub = InventoryGrpc.newBlockingStub(channel);
		asyncStub = InventoryGrpc.newStub(channel);
		
		try {
			checkInventoryQuantity();
		
			order();
		
			orderHistory();
		
		}catch (StatusRuntimeException e) {
			e.getStatus();
			
		}finally{
			try {
				channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	// Discover service
	private static void discoverInventoryService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Inventory Service resolved: " + event.getInfo());

					InventoryInfo = event.getInfo();

					int port = InventoryInfo.getPort();

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + InventoryInfo.getNiceTextString());
					System.out.println("\t host: " + InventoryInfo.getHostAddresses()[0]);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Inventory Service removed: " + event.getInfo());
				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Inventory Service added: " + event.getInfo());
				}
			});

			// Wait a bit
			Thread.sleep(1000);

			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void checkInventoryQuantity() {

		InventoryQtyRequest request = InventoryQtyRequest.newBuilder().setTotalQty(10).build();

		StreamObserver<InventoryQtyResponse> responseObserver = new StreamObserver<InventoryQtyResponse>() {

			int count =0 ;

			@Override
			public void onNext(InventoryQtyResponse value) {
				System.out.println("receiving product No: " + value.getProductNo());
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ... received "+ count+ " product Nos");
			}

		};

		asyncStub.checkInventoryQuantity(request, responseObserver);

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void order() {

		StreamObserver<OrderResponse> responseObserver = new StreamObserver<OrderResponse>() {

			int count =0 ;

			@Override
			public void onNext(OrderResponse value) {
				System.out.println("receiving product No: " + value.getProductNo() + ", orderQty: "+ value.getOrderQty() + ", afterQty: "+ value.getAfterQty() );
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ... received "+ count+ " product Nos");
			}

		};

		StreamObserver<OrderRequest> requestObserver = asyncStub.order(responseObserver);

		try {

			requestObserver.onNext(OrderRequest.newBuilder().setProductNo("A001").setOrderQty(10).build());
			requestObserver.onNext(OrderRequest.newBuilder().setProductNo("B001").setOrderQty(10).build());
			requestObserver.onNext(OrderRequest.newBuilder().setProductNo("C001").setOrderQty(10).build());
			requestObserver.onNext(OrderRequest.newBuilder().setProductNo("D001").setOrderQty(10).build());
			requestObserver.onNext(OrderRequest.newBuilder().setProductNo("E001").setOrderQty(10).build());

			// Mark the end of requests
			requestObserver.onCompleted();

			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}


		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void orderHistory() {
		String startDate = "01/01/2023";
		String endDate = "31/01/2023";
		String productNo = "A001";
	
		OrderHisRequest req = OrderHisRequest.newBuilder().setStartDate(startDate).setEndDate(endDate).setProductNo(productNo).build();

		OrderHisResponse response = blockingStub.orderHistory(req);

		System.out.println("productNo: " + productNo + ", Duration: " + startDate + " - " + endDate
							+ ", totalQty: " + response.getTotalQty()+ ", totalPrice: " + response.getTotalPrice());
		
	}
}
