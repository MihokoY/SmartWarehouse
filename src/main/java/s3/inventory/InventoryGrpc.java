package s3.inventory;

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
    comments = "Source: 3_inventory.proto")
public final class InventoryGrpc {

  private InventoryGrpc() {}

  public static final String SERVICE_NAME = "inventory.Inventory";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<s3.inventory.InventoryQtyRequest,
      s3.inventory.InventoryQtyResponse> getCheckInventoryQuantityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkInventoryQuantity",
      requestType = s3.inventory.InventoryQtyRequest.class,
      responseType = s3.inventory.InventoryQtyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<s3.inventory.InventoryQtyRequest,
      s3.inventory.InventoryQtyResponse> getCheckInventoryQuantityMethod() {
    io.grpc.MethodDescriptor<s3.inventory.InventoryQtyRequest, s3.inventory.InventoryQtyResponse> getCheckInventoryQuantityMethod;
    if ((getCheckInventoryQuantityMethod = InventoryGrpc.getCheckInventoryQuantityMethod) == null) {
      synchronized (InventoryGrpc.class) {
        if ((getCheckInventoryQuantityMethod = InventoryGrpc.getCheckInventoryQuantityMethod) == null) {
          InventoryGrpc.getCheckInventoryQuantityMethod = getCheckInventoryQuantityMethod = 
              io.grpc.MethodDescriptor.<s3.inventory.InventoryQtyRequest, s3.inventory.InventoryQtyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "inventory.Inventory", "checkInventoryQuantity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s3.inventory.InventoryQtyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s3.inventory.InventoryQtyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new InventoryMethodDescriptorSupplier("checkInventoryQuantity"))
                  .build();
          }
        }
     }
     return getCheckInventoryQuantityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<s3.inventory.OrderRequest,
      s3.inventory.OrderResponse> getOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "order",
      requestType = s3.inventory.OrderRequest.class,
      responseType = s3.inventory.OrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<s3.inventory.OrderRequest,
      s3.inventory.OrderResponse> getOrderMethod() {
    io.grpc.MethodDescriptor<s3.inventory.OrderRequest, s3.inventory.OrderResponse> getOrderMethod;
    if ((getOrderMethod = InventoryGrpc.getOrderMethod) == null) {
      synchronized (InventoryGrpc.class) {
        if ((getOrderMethod = InventoryGrpc.getOrderMethod) == null) {
          InventoryGrpc.getOrderMethod = getOrderMethod = 
              io.grpc.MethodDescriptor.<s3.inventory.OrderRequest, s3.inventory.OrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "inventory.Inventory", "order"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s3.inventory.OrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s3.inventory.OrderResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new InventoryMethodDescriptorSupplier("order"))
                  .build();
          }
        }
     }
     return getOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<s3.inventory.OrderHisRequest,
      s3.inventory.OrderHisResponse> getOrderHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "orderHistory",
      requestType = s3.inventory.OrderHisRequest.class,
      responseType = s3.inventory.OrderHisResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<s3.inventory.OrderHisRequest,
      s3.inventory.OrderHisResponse> getOrderHistoryMethod() {
    io.grpc.MethodDescriptor<s3.inventory.OrderHisRequest, s3.inventory.OrderHisResponse> getOrderHistoryMethod;
    if ((getOrderHistoryMethod = InventoryGrpc.getOrderHistoryMethod) == null) {
      synchronized (InventoryGrpc.class) {
        if ((getOrderHistoryMethod = InventoryGrpc.getOrderHistoryMethod) == null) {
          InventoryGrpc.getOrderHistoryMethod = getOrderHistoryMethod = 
              io.grpc.MethodDescriptor.<s3.inventory.OrderHisRequest, s3.inventory.OrderHisResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "inventory.Inventory", "orderHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s3.inventory.OrderHisRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s3.inventory.OrderHisResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new InventoryMethodDescriptorSupplier("orderHistory"))
                  .build();
          }
        }
     }
     return getOrderHistoryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InventoryStub newStub(io.grpc.Channel channel) {
    return new InventoryStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InventoryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InventoryBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InventoryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InventoryFutureStub(channel);
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static abstract class InventoryImplBase implements io.grpc.BindableService {

    /**
     */
    public void checkInventoryQuantity(s3.inventory.InventoryQtyRequest request,
        io.grpc.stub.StreamObserver<s3.inventory.InventoryQtyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckInventoryQuantityMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s3.inventory.OrderRequest> order(
        io.grpc.stub.StreamObserver<s3.inventory.OrderResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getOrderMethod(), responseObserver);
    }

    /**
     */
    public void orderHistory(s3.inventory.OrderHisRequest request,
        io.grpc.stub.StreamObserver<s3.inventory.OrderHisResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderHistoryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckInventoryQuantityMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                s3.inventory.InventoryQtyRequest,
                s3.inventory.InventoryQtyResponse>(
                  this, METHODID_CHECK_INVENTORY_QUANTITY)))
          .addMethod(
            getOrderMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                s3.inventory.OrderRequest,
                s3.inventory.OrderResponse>(
                  this, METHODID_ORDER)))
          .addMethod(
            getOrderHistoryMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                s3.inventory.OrderHisRequest,
                s3.inventory.OrderHisResponse>(
                  this, METHODID_ORDER_HISTORY)))
          .build();
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class InventoryStub extends io.grpc.stub.AbstractStub<InventoryStub> {
    private InventoryStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InventoryStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InventoryStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InventoryStub(channel, callOptions);
    }

    /**
     */
    public void checkInventoryQuantity(s3.inventory.InventoryQtyRequest request,
        io.grpc.stub.StreamObserver<s3.inventory.InventoryQtyResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getCheckInventoryQuantityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s3.inventory.OrderRequest> order(
        io.grpc.stub.StreamObserver<s3.inventory.OrderResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void orderHistory(s3.inventory.OrderHisRequest request,
        io.grpc.stub.StreamObserver<s3.inventory.OrderHisResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getOrderHistoryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class InventoryBlockingStub extends io.grpc.stub.AbstractStub<InventoryBlockingStub> {
    private InventoryBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InventoryBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InventoryBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InventoryBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<s3.inventory.InventoryQtyResponse> checkInventoryQuantity(
        s3.inventory.InventoryQtyRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getCheckInventoryQuantityMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<s3.inventory.OrderHisResponse> orderHistory(
        s3.inventory.OrderHisRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getOrderHistoryMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class InventoryFutureStub extends io.grpc.stub.AbstractStub<InventoryFutureStub> {
    private InventoryFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InventoryFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InventoryFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InventoryFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CHECK_INVENTORY_QUANTITY = 0;
  private static final int METHODID_ORDER_HISTORY = 1;
  private static final int METHODID_ORDER = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InventoryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InventoryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_INVENTORY_QUANTITY:
          serviceImpl.checkInventoryQuantity((s3.inventory.InventoryQtyRequest) request,
              (io.grpc.stub.StreamObserver<s3.inventory.InventoryQtyResponse>) responseObserver);
          break;
        case METHODID_ORDER_HISTORY:
          serviceImpl.orderHistory((s3.inventory.OrderHisRequest) request,
              (io.grpc.stub.StreamObserver<s3.inventory.OrderHisResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ORDER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.order(
              (io.grpc.stub.StreamObserver<s3.inventory.OrderResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class InventoryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InventoryBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return s3.inventory.InventoryImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Inventory");
    }
  }

  private static final class InventoryFileDescriptorSupplier
      extends InventoryBaseDescriptorSupplier {
    InventoryFileDescriptorSupplier() {}
  }

  private static final class InventoryMethodDescriptorSupplier
      extends InventoryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    InventoryMethodDescriptorSupplier(String methodName) {
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
      synchronized (InventoryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InventoryFileDescriptorSupplier())
              .addMethod(getCheckInventoryQuantityMethod())
              .addMethod(getOrderMethod())
              .addMethod(getOrderHistoryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
