// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: 3_inventory.proto

package s3.inventory;

public final class InventoryImpl {
  private InventoryImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_inventory_InventoryQtyRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_inventory_InventoryQtyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_inventory_InventoryQtyResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_inventory_InventoryQtyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_inventory_OrderRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_inventory_OrderRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_inventory_OrderResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_inventory_OrderResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_inventory_OrderHisRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_inventory_OrderHisRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_inventory_OrderHisResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_inventory_OrderHisResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\0213_inventory.proto\022\tinventory\"\'\n\023Invent" +
      "oryQtyRequest\022\020\n\010totalQty\030\001 \001(\005\")\n\024Inven" +
      "toryQtyResponse\022\021\n\tproductNo\030\001 \001(\t\"3\n\014Or" +
      "derRequest\022\021\n\tproductNo\030\001 \001(\t\022\020\n\010orderQt" +
      "y\030\002 \001(\005\"F\n\rOrderResponse\022\021\n\tproductNo\030\001 " +
      "\001(\t\022\020\n\010orderQty\030\002 \001(\005\022\020\n\010afterQty\030\003 \001(\005\"" +
      "H\n\017OrderHisRequest\022\021\n\tstartDate\030\001 \001(\t\022\017\n" +
      "\007endDate\030\002 \001(\t\022\021\n\tproductNo\030\003 \001(\t\"8\n\020Ord" +
      "erHisResponse\022\020\n\010totalQty\030\001 \001(\005\022\022\n\ntotal" +
      "Price\030\002 \001(\0022\367\001\n\tInventory\022]\n\026checkInvent" +
      "oryQuantity\022\036.inventory.InventoryQtyRequ" +
      "est\032\037.inventory.InventoryQtyResponse\"\0000\001" +
      "\022@\n\005order\022\027.inventory.OrderRequest\032\030.inv" +
      "entory.OrderResponse\"\000(\0010\001\022I\n\014orderHisto" +
      "ry\022\032.inventory.OrderHisRequest\032\033.invento" +
      "ry.OrderHisResponse\"\000B\037\n\014s3.inventoryB\rI" +
      "nventoryImplP\001b\006proto3"
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
    internal_static_inventory_InventoryQtyRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_inventory_InventoryQtyRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_inventory_InventoryQtyRequest_descriptor,
        new java.lang.String[] { "TotalQty", });
    internal_static_inventory_InventoryQtyResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_inventory_InventoryQtyResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_inventory_InventoryQtyResponse_descriptor,
        new java.lang.String[] { "ProductNo", });
    internal_static_inventory_OrderRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_inventory_OrderRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_inventory_OrderRequest_descriptor,
        new java.lang.String[] { "ProductNo", "OrderQty", });
    internal_static_inventory_OrderResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_inventory_OrderResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_inventory_OrderResponse_descriptor,
        new java.lang.String[] { "ProductNo", "OrderQty", "AfterQty", });
    internal_static_inventory_OrderHisRequest_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_inventory_OrderHisRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_inventory_OrderHisRequest_descriptor,
        new java.lang.String[] { "StartDate", "EndDate", "ProductNo", });
    internal_static_inventory_OrderHisResponse_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_inventory_OrderHisResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_inventory_OrderHisResponse_descriptor,
        new java.lang.String[] { "TotalQty", "TotalPrice", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
