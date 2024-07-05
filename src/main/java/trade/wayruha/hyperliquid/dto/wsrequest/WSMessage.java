package trade.wayruha.hyperliquid.dto.wsrequest;


import lombok.Data;

@Data
public class WSMessage {
  private final String method;
  private final Long id;

  public WSMessage(String method) {
    this.id = null;
    this.method = method;
  }
}