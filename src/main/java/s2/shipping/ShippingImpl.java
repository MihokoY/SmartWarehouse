// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: 2_shipping.proto

package s2.shipping;

public final class ShippingImpl {
  private ShippingImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_shipping_ShippingQtyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_shipping_ShippingQtyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_shipping_ShippingQtyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_shipping_ShippingQtyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_shipping_UpdateLocRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_shipping_UpdateLocRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_shipping_UpdateLocResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_shipping_UpdateLocResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\0202_shipping.proto\022\010shipping\"<\n\022Shipping" +
      "QtyRequest\022\021\n\tproductNo\030\001 \001(\t\022\023\n\013shippin" +
      "gQty\030\002 \001(\005\"&\n\023ShippingQtyResponse\022\017\n\007mes" +
      "sage\030\001 \001(\t\"*\n\020UpdateLocRequest\022\026\n\016produc" +
      "tIndivNo\030\001 \001(\t\"9\n\021UpdateLocResponse\022\022\n\nl" +
      "ocationNo\030\001 \001(\t\022\020\n\010availNum\030\002 \001(\0052\265\001\n\010Sh" +
      "ipping\022X\n\025checkShippingQuantity\022\034.shippi" +
      "ng.ShippingQtyRequest\032\035.shipping.Shippin" +
      "gQtyResponse\"\000(\001\022O\n\016updateLocation\022\032.shi" +
      "pping.UpdateLocRequest\032\033.shipping.Update" +
      "LocResponse\"\000(\0010\001B\035\n\013s2.shippingB\014Shippi" +
      "ngImplP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_shipping_ShippingQtyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_shipping_ShippingQtyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_shipping_ShippingQtyRequest_descriptor,
        new java.lang.String[] { "ProductNo", "ShippingQty", });
    internal_static_shipping_ShippingQtyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_shipping_ShippingQtyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_shipping_ShippingQtyResponse_descriptor,
        new java.lang.String[] { "Message", });
    internal_static_shipping_UpdateLocRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_shipping_UpdateLocRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_shipping_UpdateLocRequest_descriptor,
        new java.lang.String[] { "ProductIndivNo", });
    internal_static_shipping_UpdateLocResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_shipping_UpdateLocResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_shipping_UpdateLocResponse_descriptor,
        new java.lang.String[] { "LocationNo", "AvailNum", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
