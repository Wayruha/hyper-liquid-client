package trade.wayruha.hyperliquid.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import trade.wayruha.hyperliquid.HyperLiquidConfig;
import trade.wayruha.hyperliquid.config.ApiClient;
import trade.wayruha.hyperliquid.dto.wsresponse.OrderBookUpdate;
import trade.wayruha.hyperliquid.dto.wsrequest.Subscription;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WebSocketClientFactory {
  private final HyperLiquidConfig config;
  private final ApiClient apiClient;
  @Setter
  private ObjectMapper objectMapper;

  public WebSocketClientFactory(HyperLiquidConfig config) {
    this.apiClient = new ApiClient(config);
    this.config = config;
    this.objectMapper = config.getObjectMapper();
  }

  //public subscriptions
  public WebSocketSubscriptionClient<OrderBookUpdate> orderBookSubscription(Collection<String> coins, WebSocketCallback<OrderBookUpdate> callback) {
    final Set<Subscription> channels = coins.stream().map(Subscription.OrderBook::new).collect(Collectors.toSet());
    final WebSocketSubscriptionClient<OrderBookUpdate> client = new WebSocketSubscriptionClient<>(apiClient, objectMapper, callback);
    client.connect(channels);
    return client;
  }
}