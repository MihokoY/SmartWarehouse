package s2.shipping;

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
import s2.shipping.ShippingGrpc.ShippingBlockingStub;
import s2.shipping.ShippingGrpc.ShippingStub;

public class ShippingClient {

	private static ShippingBlockingStub blockingStub;
	private static ShippingStub asyncStub;
	
	private static ServiceInfo ShippingInfo;
	
	public static void main(String[] args) {
		
		//String shipping_service_type = "_shipping._tcp.local.";
		//discoverShippingService(shipping_service_type);
		
		//String host = ShippingInfo.getHostAddresses()[0];
		//int port = ShippingInfo.getPort();
		
		String host = "localhost";
		int port = 50052;
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		blockingStub = ShippingGrpc.newBlockingStub(channel);
		asyncStub = ShippingGrpc.newStub(channel);
		
		try {
			checkShippingQuantity();
		
			updateLocation();
		
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
	private static void discoverShippingService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Shipping Service resolved: " + event.getInfo());

					ShippingInfo = event.getInfo();

					int port = ShippingInfo.getPort();

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + ShippingInfo.getNiceTextString());
					System.out.println("\t host: " + ShippingInfo.getHostAddresses()[0]);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Shipping Service removed: " + event.getInfo());
				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Shipping Service added: " + event.getInfo());
				}
			});

			// Wait a bit
			Thread.sleep(2000);

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
	
	public static void checkShippingQuantity() {

		StreamObserver<ShippingQtyResponse> responseObserver = new StreamObserver<ShippingQtyResponse>() {

			@Override
			public void onNext(ShippingQtyResponse msg) {
				System.out.println("receiving message: " + msg.getMessage() );
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed.");
			}

		};


		StreamObserver<ShippingQtyRequest> requestObserver = asyncStub.checkShippingQuantity(responseObserver);
		try {
			requestObserver.onNext(ShippingQtyRequest.newBuilder().setProductNo("A002").setShippingQty(10).build());
			Thread.sleep(500);

			requestObserver.onNext(ShippingQtyRequest.newBuilder().setProductNo("B002").setShippingQty(20).build());
			Thread.sleep(500);

			requestObserver.onNext(ShippingQtyRequest.newBuilder().setProductNo("C002").setShippingQty(30).build());
			Thread.sleep(500);

			requestObserver.onNext(ShippingQtyRequest.newBuilder().setProductNo("D002").setShippingQty(40).build());
			Thread.sleep(500);

			requestObserver.onNext(ShippingQtyRequest.newBuilder().setProductNo("E002").setShippingQty(50).build());
			Thread.sleep(500);


			// Mark the end of requests
			requestObserver.onCompleted();
			
			Thread.sleep(10000);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	
	public static void updateLocation() {


		StreamObserver<UpdateLocResponse> responseObserver = new StreamObserver<UpdateLocResponse>() {

			int count =0 ;

			@Override
			public void onNext(UpdateLocResponse msg) {
				System.out.println("receiving product location No: " + msg.getLocationNo() + ", Availability: "+ msg.getAvailNum() );
				count += 1;
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("stream is completed ... received "+ count+ " location numbers");
			}

		};



		StreamObserver<UpdateLocRequest> requestObserver = asyncStub.updateLocation(responseObserver);

		try {

			requestObserver.onNext(UpdateLocRequest.newBuilder().setProductIndivNo("A001-01").build());
			requestObserver.onNext(UpdateLocRequest.newBuilder().setProductIndivNo("B001-01").build());
			requestObserver.onNext(UpdateLocRequest.newBuilder().setProductIndivNo("C001-01").build());
			requestObserver.onNext(UpdateLocRequest.newBuilder().setProductIndivNo("D001-01").build());
			requestObserver.onNext(UpdateLocRequest.newBuilder().setProductIndivNo("E001-01").build());

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
