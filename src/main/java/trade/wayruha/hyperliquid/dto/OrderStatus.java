package trade.wayruha.hyperliquid.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
  OPEN("open"),
  FILLED("filled"),
  CANCELED("canceled"),
  TRIGGERED("triggered"),
  REJECTED("rejected"),
  MARGIN_CANCELED("marginCanceled"),
  REDUCE_ONLY_CANCELLED("reduceOnlyCanceled");

  @JsonValue
  @Getter
  private final String name;
}
