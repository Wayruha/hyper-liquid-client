package trade.wayruha.hyperliquid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import trade.wayruha.hyperliquid.dto.wsresponse.OrderBookUpdate;
import trade.wayruha.hyperliquid.dto.wsresponse.OrderUpdate;
import trade.wayruha.hyperliquid.dto.wsresponse.UserEventUpdate;
import trade.wayruha.hyperliquid.dto.wsresponse.UserFillsUpdate;
import trade.wayruha.hyperliquid.websocket.AlternativeWSClient;
import trade.wayruha.hyperliquid.websocket.WebSocketCallback;
import trade.wayruha.hyperliquid.websocket.WebSocketClientFactory;
import trade.wayruha.hyperliquid.websocket.WebSocketSubscriptionClient;

import java.util.List;
import java.util.Set;

public class WebSocketTest {
    final static String walletAddress = "";
    static final HyperLiquidConfig config = new HyperLiquidConfig(walletAddress, null);
    static final WebSocketClientFactory factory = new WebSocketClientFactory(config);

    @SneakyThrows
    public static void main(String[] args) {
        testGenericSubscription();
//        testAlternativeOrderBookUpdate();
//    testOrderUpdates();
//    testUserFills();
//    testUserEvents();
    }

    @SneakyThrows
    private static void testGenericSubscription() {
        final TypeReference<JsonNode> type = new TypeReference<>() {
        };

        final Callback<JsonNode> callback = new Callback<>(type);
        final AlternativeWSClient<JsonNode> conn = factory.createAlternativeGenericClient(callback);
        Thread.sleep(100_000);
    }

    private static void testOrderBookUpdate() throws InterruptedException {
        final TypeReference<OrderBookUpdate> type = new TypeReference<>() {
        };
        final Callback<OrderBookUpdate> callback = new Callback<>(type);
        final WebSocketSubscriptionClient<OrderBookUpdate> subscription = factory.orderBookSubscription(Set.of("BTC", "W", "SOL"), callback);
        Thread.sleep(5_000);
    }

    private static void testAlternativeOrderBookUpdate() throws InterruptedException {
        final TypeReference<OrderBookUpdate> type = new TypeReference<>() {
        };
        final Callback<OrderBookUpdate> callback = new Callback<>(type);
        final AlternativeWSClient<OrderBookUpdate> wsClient = factory.createAlternativeOrderBookSubscription(Set.of("BTC", "W", "SOL"), callback);
        Thread.sleep(1 * 60 * 60 * 1_000);
    }

    private static void testOrderUpdates() throws InterruptedException {
        final TypeReference<List<OrderUpdate>> type = new TypeReference<>() {
        };
        final Callback<List<OrderUpdate>> callback = new Callback<>(type);
        final WebSocketSubscriptionClient<List<OrderUpdate>> subscription = factory.orderUpdates(walletAddress, callback);
        Thread.sleep(5_000);
    }

    private static void testUserFills() throws InterruptedException {
        final TypeReference<UserFillsUpdate> type = new TypeReference<>() {
        };
        final Callback<UserFillsUpdate> callback = new Callback<>(type);
        final WebSocketSubscriptionClient<UserFillsUpdate> subscription = factory.userFills(walletAddress, callback);
        Thread.sleep(5_000);
    }

    private static void testUserEvents() throws InterruptedException {
        final TypeReference<UserEventUpdate> type = new TypeReference<>() {
        };
        final Callback<UserEventUpdate> callback = new Callback<>(type);
        final WebSocketSubscriptionClient<UserEventUpdate> subscription = factory.userEvents(walletAddress, callback);
        Thread.sleep(5_000);
    }

    static class Callback<T> implements WebSocketCallback<T> {
        final TypeReference<T> type;
        int counter = 0;

        public Callback(TypeReference<T> type) {
            this.type = type;
        }

        @Override
        public void onResponse(T response) {
            counter++;
            if (counter % 10 == 0) System.out.println(response);

        }

        @Override
        public TypeReference<T> getType() {
            return type;
        }
    }
}
