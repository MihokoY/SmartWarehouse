// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: 3_inventory.proto

package s3.inventory;

/**
 * Protobuf type {@code inventory.OrderHisResponse}
 */
public  final class OrderHisResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:inventory.OrderHisResponse)
    OrderHisResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use OrderHisResponse.newBuilder() to construct.
  private OrderHisResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OrderHisResponse() {
    productNo_ = "";
    totalQty_ = 0;
    totalPrice_ = 0F;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OrderHisResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            productNo_ = s;
            break;
          }
          case 16: {

            totalQty_ = input.readInt32();
            break;
          }
          case 29: {

            totalPrice_ = input.readFloat();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return s3.inventory.InventoryImpl.internal_static_inventory_OrderHisResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return s3.inventory.InventoryImpl.internal_static_inventory_OrderHisResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            s3.inventory.OrderHisResponse.class, s3.inventory.OrderHisResponse.Builder.class);
  }

  public static final int PRODUCTNO_FIELD_NUMBER = 1;
  private volatile java.lang.Object productNo_;
  /**
   * <code>string productNo = 1;</code>
   */
  public java.lang.String getProductNo() {
    java.lang.Object ref = productNo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      productNo_ = s;
      return s;
    }
  }
  /**
   * <code>string productNo = 1;</code>
   */
  public com.google.protobuf.ByteString
      getProductNoBytes() {
    java.lang.Object ref = productNo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      productNo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TOTALQTY_FIELD_NUMBER = 2;
  private int totalQty_;
  /**
   * <code>int32 totalQty = 2;</code>
   */
  public int getTotalQty() {
    return totalQty_;
  }

  public static final int TOTALPRICE_FIELD_NUMBER = 3;
  private float totalPrice_;
  /**
   * <code>float totalPrice = 3;</code>
   */
  public float getTotalPrice() {
    return totalPrice_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getProductNoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, productNo_);
    }
    if (totalQty_ != 0) {
      output.writeInt32(2, totalQty_);
    }
    if (totalPrice_ != 0F) {
      output.writeFloat(3, totalPrice_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getProductNoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, productNo_);
    }
    if (totalQty_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, totalQty_);
    }
    if (totalPrice_ != 0F) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(3, totalPrice_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof s3.inventory.OrderHisResponse)) {
      return super.equals(obj);
    }
    s3.inventory.OrderHisResponse other = (s3.inventory.OrderHisResponse) obj;

    boolean result = true;
    result = result && getProductNo()
        .equals(other.getProductNo());
    result = result && (getTotalQty()
        == other.getTotalQty());
    result = result && (
        java.lang.Float.floatToIntBits(getTotalPrice())
        == java.lang.Float.floatToIntBits(
            other.getTotalPrice()));
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PRODUCTNO_FIELD_NUMBER;
    hash = (53 * hash) + getProductNo().hashCode();
    hash = (37 * hash) + TOTALQTY_FIELD_NUMBER;
    hash = (53 * hash) + getTotalQty();
    hash = (37 * hash) + TOTALPRICE_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getTotalPrice());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static s3.inventory.OrderHisResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static s3.inventory.OrderHisResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static s3.inventory.OrderHisResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static s3.inventory.OrderHisResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static s3.inventory.OrderHisResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static s3.inventory.OrderHisResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static s3.inventory.OrderHisResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static s3.inventory.OrderHisResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static s3.inventory.OrderHisResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static s3.inventory.OrderHisResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static s3.inventory.OrderHisResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static s3.inventory.OrderHisResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(s3.inventory.OrderHisResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code inventory.OrderHisResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:inventory.OrderHisResponse)
      s3.inventory.OrderHisResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return s3.inventory.InventoryImpl.internal_static_inventory_OrderHisResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return s3.inventory.InventoryImpl.internal_static_inventory_OrderHisResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              s3.inventory.OrderHisResponse.class, s3.inventory.OrderHisResponse.Builder.class);
    }

    // Construct using s3.inventory.OrderHisResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      productNo_ = "";

      totalQty_ = 0;

      totalPrice_ = 0F;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return s3.inventory.InventoryImpl.internal_static_inventory_OrderHisResponse_descriptor;
    }

    @java.lang.Override
    public s3.inventory.OrderHisResponse getDefaultInstanceForType() {
      return s3.inventory.OrderHisResponse.getDefaultInstance();
    }

    @java.lang.Override
    public s3.inventory.OrderHisResponse build() {
      s3.inventory.OrderHisResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public s3.inventory.OrderHisResponse buildPartial() {
      s3.inventory.OrderHisResponse result = new s3.inventory.OrderHisResponse(this);
      result.productNo_ = productNo_;
      result.totalQty_ = totalQty_;
      result.totalPrice_ = totalPrice_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof s3.inventory.OrderHisResponse) {
        return mergeFrom((s3.inventory.OrderHisResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(s3.inventory.OrderHisResponse other) {
      if (other == s3.inventory.OrderHisResponse.getDefaultInstance()) return this;
      if (!other.getProductNo().isEmpty()) {
        productNo_ = other.productNo_;
        onChanged();
      }
      if (other.getTotalQty() != 0) {
        setTotalQty(other.getTotalQty());
      }
      if (other.getTotalPrice() != 0F) {
        setTotalPrice(other.getTotalPrice());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      s3.inventory.OrderHisResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (s3.inventory.OrderHisResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object productNo_ = "";
    /**
     * <code>string productNo = 1;</code>
     */
    public java.lang.String getProductNo() {
      java.lang.Object ref = productNo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        productNo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string productNo = 1;</code>
     */
    public com.google.protobuf.ByteString
        getProductNoBytes() {
      java.lang.Object ref = productNo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        productNo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string productNo = 1;</code>
     */
    public Builder setProductNo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      productNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string productNo = 1;</code>
     */
    public Builder clearProductNo() {
      
      productNo_ = getDefaultInstance().getProductNo();
      onChanged();
      return this;
    }
    /**
     * <code>string productNo = 1;</code>
     */
    public Builder setProductNoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      productNo_ = value;
      onChanged();
      return this;
    }

    private int totalQty_ ;
    /**
     * <code>int32 totalQty = 2;</code>
     */
    public int getTotalQty() {
      return totalQty_;
    }
    /**
     * <code>int32 totalQty = 2;</code>
     */
    public Builder setTotalQty(int value) {
      
      totalQty_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 totalQty = 2;</code>
     */
    public Builder clearTotalQty() {
      
      totalQty_ = 0;
      onChanged();
      return this;
    }

    private float totalPrice_ ;
    /**
     * <code>float totalPrice = 3;</code>
     */
    public float getTotalPrice() {
      return totalPrice_;
    }
    /**
     * <code>float totalPrice = 3;</code>
     */
    public Builder setTotalPrice(float value) {
      
      totalPrice_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>float totalPrice = 3;</code>
     */
    public Builder clearTotalPrice() {
      
      totalPrice_ = 0F;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:inventory.OrderHisResponse)
  }

  // @@protoc_insertion_point(class_scope:inventory.OrderHisResponse)
  private static final s3.inventory.OrderHisResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new s3.inventory.OrderHisResponse();
  }

  public static s3.inventory.OrderHisResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OrderHisResponse>
      PARSER = new com.google.protobuf.AbstractParser<OrderHisResponse>() {
    @java.lang.Override
    public OrderHisResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new OrderHisResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OrderHisResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OrderHisResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public s3.inventory.OrderHisResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

