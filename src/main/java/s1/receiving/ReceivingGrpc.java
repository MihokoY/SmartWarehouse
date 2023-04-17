package s1.receiving;

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
    comments = "Source: 1_receiving.proto")
public final class ReceivingGrpc {

  private ReceivingGrpc() {}

  public static final String SERVICE_NAME = "receiving.Receiving";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<s1.receiving.ReceivedQtyRequest,
      s1.receiving.ReceivedQtyResponse> getCheckReceivedQuantityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkReceivedQuantity",
      requestType = s1.receiving.ReceivedQtyRequest.class,
      responseType = s1.receiving.ReceivedQtyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<s1.receiving.ReceivedQtyRequest,
      s1.receiving.ReceivedQtyResponse> getCheckReceivedQuantityMethod() {
    io.grpc.MethodDescriptor<s1.receiving.ReceivedQtyRequest, s1.receiving.ReceivedQtyResponse> getCheckReceivedQuantityMethod;
    if ((getCheckReceivedQuantityMethod = ReceivingGrpc.getCheckReceivedQuantityMethod) == null) {
      synchronized (ReceivingGrpc.class) {
        if ((getCheckReceivedQuantityMethod = ReceivingGrpc.getCheckReceivedQuantityMethod) == null) {
          ReceivingGrpc.getCheckReceivedQuantityMethod = getCheckReceivedQuantityMethod = 
              io.grpc.MethodDescriptor.<s1.receiving.ReceivedQtyRequest, s1.receiving.ReceivedQtyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "receiving.Receiving", "checkReceivedQuantity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s1.receiving.ReceivedQtyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s1.receiving.ReceivedQtyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ReceivingMethodDescriptorSupplier("checkReceivedQuantity"))
                  .build();
          }
        }
     }
     return getCheckReceivedQuantityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<s1.receiving.SetLocRequest,
      s1.receiving.SetLocResponse> getSetLocationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setLocation",
      requestType = s1.receiving.SetLocRequest.class,
      responseType = s1.receiving.SetLocResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<s1.receiving.SetLocRequest,
      s1.receiving.SetLocResponse> getSetLocationMethod() {
    io.grpc.MethodDescriptor<s1.receiving.SetLocRequest, s1.receiving.SetLocResponse> getSetLocationMethod;
    if ((getSetLocationMethod = ReceivingGrpc.getSetLocationMethod) == null) {
      synchronized (ReceivingGrpc.class) {
        if ((getSetLocationMethod = ReceivingGrpc.getSetLocationMethod) == null) {
          ReceivingGrpc.getSetLocationMethod = getSetLocationMethod = 
              io.grpc.MethodDescriptor.<s1.receiving.SetLocRequest, s1.receiving.SetLocResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "receiving.Receiving", "setLocation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s1.receiving.SetLocRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s1.receiving.SetLocResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ReceivingMethodDescriptorSupplier("setLocation"))
                  .build();
          }
        }
     }
     return getSetLocationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<s1.receiving.LocationAvailRequest,
      s1.receiving.LocationAvailResponse> getCheckLocationAvailabilityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkLocationAvailability",
      requestType = s1.receiving.LocationAvailRequest.class,
      responseType = s1.receiving.LocationAvailResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<s1.receiving.LocationAvailRequest,
      s1.receiving.LocationAvailResponse> getCheckLocationAvailabilityMethod() {
    io.grpc.MethodDescriptor<s1.receiving.LocationAvailRequest, s1.receiving.LocationAvailResponse> getCheckLocationAvailabilityMethod;
    if ((getCheckLocationAvailabilityMethod = ReceivingGrpc.getCheckLocationAvailabilityMethod) == null) {
      synchronized (ReceivingGrpc.class) {
        if ((getCheckLocationAvailabilityMethod = ReceivingGrpc.getCheckLocationAvailabilityMethod) == null) {
          ReceivingGrpc.getCheckLocationAvailabilityMethod = getCheckLocationAvailabilityMethod = 
              io.grpc.MethodDescriptor.<s1.receiving.LocationAvailRequest, s1.receiving.LocationAvailResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "receiving.Receiving", "checkLocationAvailability"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s1.receiving.LocationAvailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  s1.receiving.LocationAvailResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ReceivingMethodDescriptorSupplier("checkLocationAvailability"))
                  .build();
          }
        }
     }
     return getCheckLocationAvailabilityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReceivingStub newStub(io.grpc.Channel channel) {
    return new ReceivingStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReceivingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ReceivingBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReceivingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ReceivingFutureStub(channel);
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static abstract class ReceivingImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<s1.receiving.ReceivedQtyRequest> checkReceivedQuantity(
        io.grpc.stub.StreamObserver<s1.receiving.ReceivedQtyResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCheckReceivedQuantityMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s1.receiving.SetLocRequest> setLocation(
        io.grpc.stub.StreamObserver<s1.receiving.SetLocResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getSetLocationMethod(), responseObserver);
    }

    /**
     */
    public void checkLocationAvailability(s1.receiving.LocationAvailRequest request,
        io.grpc.stub.StreamObserver<s1.receiving.LocationAvailResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckLocationAvailabilityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckReceivedQuantityMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                s1.receiving.ReceivedQtyRequest,
                s1.receiving.ReceivedQtyResponse>(
                  this, METHODID_CHECK_RECEIVED_QUANTITY)))
          .addMethod(
            getSetLocationMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                s1.receiving.SetLocRequest,
                s1.receiving.SetLocResponse>(
                  this, METHODID_SET_LOCATION)))
          .addMethod(
            getCheckLocationAvailabilityMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                s1.receiving.LocationAvailRequest,
                s1.receiving.LocationAvailResponse>(
                  this, METHODID_CHECK_LOCATION_AVAILABILITY)))
          .build();
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class ReceivingStub extends io.grpc.stub.AbstractStub<ReceivingStub> {
    private ReceivingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReceivingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReceivingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReceivingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s1.receiving.ReceivedQtyRequest> checkReceivedQuantity(
        io.grpc.stub.StreamObserver<s1.receiving.ReceivedQtyResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getCheckReceivedQuantityMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<s1.receiving.SetLocRequest> setLocation(
        io.grpc.stub.StreamObserver<s1.receiving.SetLocResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSetLocationMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void checkLocationAvailability(s1.receiving.LocationAvailRequest request,
        io.grpc.stub.StreamObserver<s1.receiving.LocationAvailResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckLocationAvailabilityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class ReceivingBlockingStub extends io.grpc.stub.AbstractStub<ReceivingBlockingStub> {
    private ReceivingBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReceivingBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReceivingBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReceivingBlockingStub(channel, callOptions);
    }

    /**
     */
    public s1.receiving.LocationAvailResponse checkLocationAvailability(s1.receiving.LocationAvailRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckLocationAvailabilityMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service Definition
   * </pre>
   */
  public static final class ReceivingFutureStub extends io.grpc.stub.AbstractStub<ReceivingFutureStub> {
    private ReceivingFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReceivingFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReceivingFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReceivingFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<s1.receiving.LocationAvailResponse> checkLocationAvailability(
        s1.receiving.LocationAvailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckLocationAvailabilityMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_LOCATION_AVAILABILITY = 0;
  private static final int METHODID_CHECK_RECEIVED_QUANTITY = 1;
  private static final int METHODID_SET_LOCATION = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReceivingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReceivingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_LOCATION_AVAILABILITY:
          serviceImpl.checkLocationAvailability((s1.receiving.LocationAvailRequest) request,
              (io.grpc.stub.StreamObserver<s1.receiving.LocationAvailResponse>) responseObserver);
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
        case METHODID_CHECK_RECEIVED_QUANTITY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.checkReceivedQuantity(
              (io.grpc.stub.StreamObserver<s1.receiving.ReceivedQtyResponse>) responseObserver);
        case METHODID_SET_LOCATION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.setLocation(
              (io.grpc.stub.StreamObserver<s1.receiving.SetLocResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ReceivingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReceivingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return s1.receiving.ReceivingImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Receiving");
    }
  }

  private static final class ReceivingFileDescriptorSupplier
      extends ReceivingBaseDescriptorSupplier {
    ReceivingFileDescriptorSupplier() {}
  }

  private static final class ReceivingMethodDescriptorSupplier
      extends ReceivingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReceivingMethodDescriptorSupplier(String methodName) {
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
      synchronized (ReceivingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReceivingFileDescriptorSupplier())
              .addMethod(getCheckReceivedQuantityMethod())
              .addMethod(getSetLocationMethod())
              .addMethod(getCheckLocationAvailabilityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
