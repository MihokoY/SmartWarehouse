package s3.inventory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import s1.receiving.SetLocRequest;
import s1.receiving.SetLocResponse;
import s3.inventory.InventoryGrpc.InventoryBlockingStub;
import s3.inventory.InventoryGrpc.InventoryStub;

public class InventoryClient {
	private static InventoryBlockingStub blockingStub;
	private static InventoryStub asyncStub;
	
	public static void main(String[] args) {
		String host = "localhost";
		int port = 50053;
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		blockingStub = InventoryGrpc.newBlockingStub(channel);
		asyncStub = InventoryGrpc.newStub(channel);
		
		try {
			checkInventoryQuantity();
		
			order();
		
			//orderHistory();
		
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
}
