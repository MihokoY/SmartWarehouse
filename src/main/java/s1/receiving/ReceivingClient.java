package s1.receiving;

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
import s1.receiving.ReceivingGrpc.ReceivingBlockingStub;
import s1.receiving.ReceivingGrpc.ReceivingStub;

public class ReceivingClient{
	
	private static ReceivingBlockingStub blockingStub;
	private static ReceivingStub asyncStub;
	
	private static ServiceInfo ReceivingInfo;
	
	public static void main(String[] args) {
		
		String receiving_service_type = "_receiving._tcp.local.";
		discoverReceivingService(receiving_service_type);
		
		String host = ReceivingInfo.getHostAddresses()[0];
		int port = ReceivingInfo.getPort();
		
		//String host = "localhost";
		//int port = 50051;
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		blockingStub = ReceivingGrpc.newBlockingStub(channel);
		asyncStub = ReceivingGrpc.newStub(channel);
		
		try {
			checkReceivedQuantity();
		
			setLocation();
		
			checkLocationAvailability();
		
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
	private static void discoverReceivingService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Receiving Service resolved: " + event.getInfo());

					ReceivingInfo = event.getInfo();

					int port = ReceivingInfo.getPort();

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + ReceivingInfo.getNiceTextString());
					System.out.println("\t host: " + ReceivingInfo.getHostAddresses()[0]);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Receiving Service removed: " + event.getInfo());
				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Receiving Service added: " + event.getInfo());
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
	
	
	public static void checkReceivedQuantity() {

		StreamObserver<ReceivedQtyResponse> responseObserver = new StreamObserver<ReceivedQtyResponse>() {

			@Override
			public void onNext(ReceivedQtyResponse msg) {
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


		StreamObserver<ReceivedQtyRequest> requestObserver = asyncStub.checkReceivedQuantity(responseObserver);
		try {
			requestObserver.onNext(ReceivedQtyRequest.newBuilder().setProductNo("A001").setReceivedQty(10).build());
			Thread.sleep(500);

			requestObserver.onNext(ReceivedQtyRequest.newBuilder().setProductNo("B001").setReceivedQty(20).build());
			Thread.sleep(500);

			requestObserver.onNext(ReceivedQtyRequest.newBuilder().setProductNo("C001").setReceivedQty(30).build());
			Thread.sleep(500);

			requestObserver.onNext(ReceivedQtyRequest.newBuilder().setProductNo("D001").setReceivedQty(40).build());
			Thread.sleep(500);

			requestObserver.onNext(ReceivedQtyRequest.newBuilder().setProductNo("E001").setReceivedQty(51).build());
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
	
	
	public static void setLocation() {

		StreamObserver<SetLocResponse> responseObserver = new StreamObserver<SetLocResponse>() {

			int count =0 ;

			@Override
			public void onNext(SetLocResponse msg) {
				System.out.println("receiving product indivisual No: " + msg.getProductIndivNo() + ", location No: "+ msg.getLocationNo() );
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



		StreamObserver<SetLocRequest> requestObserver = asyncStub.setLocation(responseObserver);

		try {

			requestObserver.onNext(SetLocRequest.newBuilder().setProductNo("A001").setProductIndivNo("A001-04").build());
			requestObserver.onNext(SetLocRequest.newBuilder().setProductNo("B001").setProductIndivNo("B001-04").build());
			requestObserver.onNext(SetLocRequest.newBuilder().setProductNo("C001").setProductIndivNo("C001-04").build());
			requestObserver.onNext(SetLocRequest.newBuilder().setProductNo("D001").setProductIndivNo("D001-04").build());
			requestObserver.onNext(SetLocRequest.newBuilder().setProductNo("E001").setProductIndivNo("E001-04").build());

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
	
	
	public static void checkLocationAvailability() {
		String locatNo = "AA01";

		
		LocationAvailRequest req = LocationAvailRequest.newBuilder().setLocationNo(locatNo).build();

		LocationAvailResponse response = blockingStub.checkLocationAvailability(req);

		System.out.println("Location No: " + locatNo + ", Availability: " + response.getAvailNum());
		
	}
}