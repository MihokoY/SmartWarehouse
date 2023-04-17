package s2.shipping;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import s2.shipping.ShippingGrpc.ShippingImplBase;

public class ShippingServer extends ShippingImplBase {
	public static void main(String[] args) {
		ShippingServer shippingserver = new ShippingServer();
		int port = 50052;
		
		Server server;
		try {
			server = ServerBuilder.forPort(port).addService(shippingserver).build().start();
			System.out.println("Shipping Server started, listening on " + port);
			server.awaitTermination();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public StreamObserver<ShippingQtyRequest> checkShippingQuantity(
			StreamObserver<ShippingQtyResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.checkShippingQuantity(responseObserver);
	}

	@Override
	public StreamObserver<updateLocRequest> updateLocation(StreamObserver<updateLocResponse> responseObserver) {
		// TODO Auto-generated method stub
		return super.updateLocation(responseObserver);
	}
}
