package s2.shipping;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Service Definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: 2_shipping.proto")
public final class ShippingGrpc {

  private ShippingGrpc() {}

  public static final String SERVICE_NAME = "shipping.Shipping";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<s2.shipping.ShippingQtyRequest,
      s2.shipping.ShippingQtyResponse> getCheckShippingQuantityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkShippingQuantity",
      requestType = s2.shipping.ShippingQtyRequest.class,
      responseType = s2.shipping.ShippingQtyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<s2.shipping.ShippingQtyRequest,
      s2.shipping.ShippingQtyResponse> getCheckShippingQuantityMethod() {
    io.grpc.MethodDescriptor<s2.shipping.ShippingQtyRequest, s2.shipping.ShippingQtyResponse> getCheckShippingQuantityMethod;
    if ((getCheckShippingQuantityMethod = ShippingGrpc.getCheckShippingQuantityMethod) == null) {
      synchronized (ShippingGrpc.class) {
        if ((getCheckShippingQuantityMethod = ShippingGrpc.getCheckShippingQuantityMethod) == null) {
          ShippingGrpc.getCheckShippingQuantityMethod = getCheckShippingQuantityMethod = 
              io.grpc.MethodDescriptor.<s2.shipping.ShippingQtyRequest, s2.shipping.ShippingQtyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "shipping.Shipping", "checkShippingQuantity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s2.shipping.ShippingQtyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s2.shipping.ShippingQtyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ShippingMethodDescriptorSupplier("checkShippingQuantity"))
                  .build();
          }
        }
     }
     return getCheckShippingQuantityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<s2.shipping.UpdateLocRequest,
      s2.shipping.UpdateLocResponse> getUpdateLocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateLocation",
      requestType = s2.shipping.UpdateLocRequest.class,
      responseType = s2.shipping.UpdateLocResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<s2.shipping.UpdateLocRequest,
      s2.shipping.UpdateLocResponse> getUpdateLocationMethod() {
    io.grpc.MethodDescriptor<s2.shipping.UpdateLocRequest, s2.shipping.UpdateLocResponse> getUpdateLocationMethod;
    if ((getUpdateLocationMethod = ShippingGrpc.getUpdateLocationMethod) == null) {
      synchronized (ShippingGrpc.class) {
        if ((getUpdateLocationMethod = ShippingGrpc.getUpdateLocationMethod) == null) {
          ShippingGrpc.getUpdateLocationMethod = getUpdateLocationMethod = 
              io.grpc.MethodDescriptor.<s2.shipping.UpdateLocRequest, s2.shipping.UpdateLocResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "shipping.Shipping", "updateLocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s2.shipping.UpdateLocRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s2.shipping.UpdateLocResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ShippingMethodDescriptorSupplier("updateLocation"))
                  .build();
          }
        }
     }
     return getUpdateLocationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ShippingStub newStub(io.grpc.Channel channel) {
    return new ShippingStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ShippingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ShippingBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ShippingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ShippingFutureStub(channel);
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static abstract class ShippingImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<s2.shipping.ShippingQtyRequest> checkShippingQuantity(
        io.grpc.stub.StreamObserver<s2.shipping.ShippingQtyResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCheckShippingQuantityMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s2.shipping.UpdateLocRequest> updateLocation(
        io.grpc.stub.StreamObserver<s2.shipping.UpdateLocResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getUpdateLocationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckShippingQuantityMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                s2.shipping.ShippingQtyRequest,
                s2.shipping.ShippingQtyResponse>(
                  this, METHODID_CHECK_SHIPPING_QUANTITY)))
          .addMethod(
            getUpdateLocationMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                s2.shipping.UpdateLocRequest,
                s2.shipping.UpdateLocResponse>(
                  this, METHODID_UPDATE_LOCATION)))
          .build();
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class ShippingStub extends io.grpc.stub.AbstractStub<ShippingStub> {
    private ShippingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ShippingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShippingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ShippingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s2.shipping.ShippingQtyRequest> checkShippingQuantity(
        io.grpc.stub.StreamObserver<s2.shipping.ShippingQtyResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getCheckShippingQuantityMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s2.shipping.UpdateLocRequest> updateLocation(
        io.grpc.stub.StreamObserver<s2.shipping.UpdateLocResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getUpdateLocationMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class ShippingBlockingStub extends io.grpc.stub.AbstractStub<ShippingBlockingStub> {
    private ShippingBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ShippingBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShippingBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ShippingBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class ShippingFutureStub extends io.grpc.stub.AbstractStub<ShippingFutureStub> {
    private ShippingFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ShippingFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShippingFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ShippingFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CHECK_SHIPPING_QUANTITY = 0;
  private static final int METHODID_UPDATE_LOCATION = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ShippingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ShippingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_SHIPPING_QUANTITY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.checkShippingQuantity(
              (io.grpc.stub.StreamObserver<s2.shipping.ShippingQtyResponse>) responseObserver);
        case METHODID_UPDATE_LOCATION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.updateLocation(
              (io.grpc.stub.StreamObserver<s2.shipping.UpdateLocResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ShippingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ShippingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return s2.shipping.ShippingImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Shipping");
    }
  }

  private static final class ShippingFileDescriptorSupplier
      extends ShippingBaseDescriptorSupplier {
    ShippingFileDescriptorSupplier() {}
  }

  private static final class ShippingMethodDescriptorSupplier
      extends ShippingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ShippingMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ShippingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ShippingFileDescriptorSupplier())
              .addMethod(getCheckShippingQuantityMethod())
              .addMethod(getUpdateLocationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
