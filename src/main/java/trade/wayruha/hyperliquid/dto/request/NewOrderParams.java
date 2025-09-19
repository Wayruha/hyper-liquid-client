package trade.wayruha.hyperliquid.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Value;
import trade.wayruha.hyperliquid.dto.OrderSide;

import java.io.IOException;
import java.math.BigDecimal;

@Value
@JsonPropertyOrder({"a", "b", "p", "s", "r", "t"})
public class NewOrderParams {
  @JsonProperty("a")
  int assetId;
  @JsonProperty("b")
  boolean buy;
  @JsonProperty("p")
  @JsonSerialize(using = BigDecimalAsStringSerializer.class)
  BigDecimal price;
  @JsonProperty("s")
  @JsonSerialize(using = BigDecimalAsStringSerializer.class)
  BigDecimal size;
  @JsonProperty("r")
  boolean reduceOnly;
  @JsonProperty("t")
  OrderType orderType;
  @JsonProperty("c")
  String clientOrderId;

  public NewOrderParams(int assetId, OrderSide orderSide, BigDecimal price, BigDecimal size, boolean reduceOnly, OrderType type, String clientOrderId) {
    this.assetId = assetId;
    this.buy = orderSide.equals(OrderSide.BID);
    this.price = price;
    this.size = size;
    this.reduceOnly = reduceOnly;
    this.orderType = type;
    this.clientOrderId = clientOrderId;
  }

  public NewOrderParams(int assetId, OrderSide orderSide, BigDecimal price, BigDecimal size, boolean reduceOnly, OrderType type) {
   this(assetId, orderSide, price, size, reduceOnly, type, null);
  }

  public static class BigDecimalAsStringSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
      jsonGenerator.writeString(value.toPlainString());
    }
  }
}

