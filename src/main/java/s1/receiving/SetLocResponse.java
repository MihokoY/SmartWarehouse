// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: 1_receiving.proto

package s1.receiving;

/**
 * Protobuf type {@code receiving.SetLocResponse}
 */
public  final class SetLocResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:receiving.SetLocResponse)
    SetLocResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SetLocResponse.newBuilder() to construct.
  private SetLocResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetLocResponse() {
    productIndivNo_ = "";
    locationNo_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SetLocResponse(
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

            productIndivNo_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            locationNo_ = s;
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
    return s1.receiving.ReceivingImpl.internal_static_receiving_SetLocResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return s1.receiving.ReceivingImpl.internal_static_receiving_SetLocResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            s1.receiving.SetLocResponse.class, s1.receiving.SetLocResponse.Builder.class);
  }

  public static final int PRODUCTINDIVNO_FIELD_NUMBER = 1;
  private volatile java.lang.Object productIndivNo_;
  /**
   * <code>string productIndivNo = 1;</code>
   */
  public java.lang.String getProductIndivNo() {
    java.lang.Object ref = productIndivNo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      productIndivNo_ = s;
      return s;
    }
  }
  /**
   * <code>string productIndivNo = 1;</code>
   */
  public com.google.protobuf.ByteString
      getProductIndivNoBytes() {
    java.lang.Object ref = productIndivNo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      productIndivNo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LOCATIONNO_FIELD_NUMBER = 2;
  private volatile java.lang.Object locationNo_;
  /**
   * <code>string locationNo = 2;</code>
   */
  public java.lang.String getLocationNo() {
    java.lang.Object ref = locationNo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      locationNo_ = s;
      return s;
    }
  }
  /**
   * <code>string locationNo = 2;</code>
   */
  public com.google.protobuf.ByteString
      getLocationNoBytes() {
    java.lang.Object ref = locationNo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      locationNo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getProductIndivNoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, productIndivNo_);
    }
    if (!getLocationNoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, locationNo_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getProductIndivNoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, productIndivNo_);
    }
    if (!getLocationNoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, locationNo_);
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
    if (!(obj instanceof s1.receiving.SetLocResponse)) {
      return super.equals(obj);
    }
    s1.receiving.SetLocResponse other = (s1.receiving.SetLocResponse) obj;

    boolean result = true;
    result = result && getProductIndivNo()
        .equals(other.getProductIndivNo());
    result = result && getLocationNo()
        .equals(other.getLocationNo());
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
    hash = (37 * hash) + PRODUCTINDIVNO_FIELD_NUMBER;
    hash = (53 * hash) + getProductIndivNo().hashCode();
    hash = (37 * hash) + LOCATIONNO_FIELD_NUMBER;
    hash = (53 * hash) + getLocationNo().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static s1.receiving.SetLocResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static s1.receiving.SetLocResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static s1.receiving.SetLocResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static s1.receiving.SetLocResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static s1.receiving.SetLocResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static s1.receiving.SetLocResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static s1.receiving.SetLocResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static s1.receiving.SetLocResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static s1.receiving.SetLocResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static s1.receiving.SetLocResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static s1.receiving.SetLocResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static s1.receiving.SetLocResponse parseFrom(
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
  public static Builder newBuilder(s1.receiving.SetLocResponse prototype) {
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
   * Protobuf type {@code receiving.SetLocResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:receiving.SetLocResponse)
      s1.receiving.SetLocResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return s1.receiving.ReceivingImpl.internal_static_receiving_SetLocResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return s1.receiving.ReceivingImpl.internal_static_receiving_SetLocResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              s1.receiving.SetLocResponse.class, s1.receiving.SetLocResponse.Builder.class);
    }

    // Construct using s1.receiving.SetLocResponse.newBuilder()
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
      productIndivNo_ = "";

      locationNo_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return s1.receiving.ReceivingImpl.internal_static_receiving_SetLocResponse_descriptor;
    }

    @java.lang.Override
    public s1.receiving.SetLocResponse getDefaultInstanceForType() {
      return s1.receiving.SetLocResponse.getDefaultInstance();
    }

    @java.lang.Override
    public s1.receiving.SetLocResponse build() {
      s1.receiving.SetLocResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public s1.receiving.SetLocResponse buildPartial() {
      s1.receiving.SetLocResponse result = new s1.receiving.SetLocResponse(this);
      result.productIndivNo_ = productIndivNo_;
      result.locationNo_ = locationNo_;
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
      if (other instanceof s1.receiving.SetLocResponse) {
        return mergeFrom((s1.receiving.SetLocResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(s1.receiving.SetLocResponse other) {
      if (other == s1.receiving.SetLocResponse.getDefaultInstance()) return this;
      if (!other.getProductIndivNo().isEmpty()) {
        productIndivNo_ = other.productIndivNo_;
        onChanged();
      }
      if (!other.getLocationNo().isEmpty()) {
        locationNo_ = other.locationNo_;
        onChanged();
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
      s1.receiving.SetLocResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (s1.receiving.SetLocResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object productIndivNo_ = "";
    /**
     * <code>string productIndivNo = 1;</code>
     */
    public java.lang.String getProductIndivNo() {
      java.lang.Object ref = productIndivNo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        productIndivNo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string productIndivNo = 1;</code>
     */
    public com.google.protobuf.ByteString
        getProductIndivNoBytes() {
      java.lang.Object ref = productIndivNo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        productIndivNo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string productIndivNo = 1;</code>
     */
    public Builder setProductIndivNo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      productIndivNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string productIndivNo = 1;</code>
     */
    public Builder clearProductIndivNo() {
      
      productIndivNo_ = getDefaultInstance().getProductIndivNo();
      onChanged();
      return this;
    }
    /**
     * <code>string productIndivNo = 1;</code>
     */
    public Builder setProductIndivNoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      productIndivNo_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object locationNo_ = "";
    /**
     * <code>string locationNo = 2;</code>
     */
    public java.lang.String getLocationNo() {
      java.lang.Object ref = locationNo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        locationNo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string locationNo = 2;</code>
     */
    public com.google.protobuf.ByteString
        getLocationNoBytes() {
      java.lang.Object ref = locationNo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        locationNo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string locationNo = 2;</code>
     */
    public Builder setLocationNo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      locationNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string locationNo = 2;</code>
     */
    public Builder clearLocationNo() {
      
      locationNo_ = getDefaultInstance().getLocationNo();
      onChanged();
      return this;
    }
    /**
     * <code>string locationNo = 2;</code>
     */
    public Builder setLocationNoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      locationNo_ = value;
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


    // @@protoc_insertion_point(builder_scope:receiving.SetLocResponse)
  }

  // @@protoc_insertion_point(class_scope:receiving.SetLocResponse)
  private static final s1.receiving.SetLocResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new s1.receiving.SetLocResponse();
  }

  public static s1.receiving.SetLocResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SetLocResponse>
      PARSER = new com.google.protobuf.AbstractParser<SetLocResponse>() {
    @java.lang.Override
    public SetLocResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SetLocResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetLocResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetLocResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public s1.receiving.SetLocResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

