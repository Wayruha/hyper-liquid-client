package trade.wayruha.hyperliquid.dto.request;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class CancelOrderAction extends BaseAction {
  private final List<CancelOrderParams> cancels;

  public CancelOrderAction() {
    super("cancel");
    cancels = new ArrayList<>();
  }

  public void addCancel(int coin, long oid) {
    cancels.add(new CancelOrderParams(coin, oid));
  }

  public void addCancel(CancelOrderParams cancelDetails) {
    cancels.add(cancelDetails);
  }

}
