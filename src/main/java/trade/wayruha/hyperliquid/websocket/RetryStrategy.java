package trade.wayruha.hyperliquid.websocket;

import java.util.concurrent.Callable;

public interface RetryStrategy<In, Out> {
    In call(Callable<Out> function);
}