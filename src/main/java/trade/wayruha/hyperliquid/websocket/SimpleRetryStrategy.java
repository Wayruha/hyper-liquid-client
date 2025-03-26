package trade.wayruha.hyperliquid.websocket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class SimpleRetryStrategy implements RetryStrategy<Boolean, Boolean> {
    private final int maxRetries;
    private final long waitTimeMs;

    public SimpleRetryStrategy(int maxRetries, long waitTimeMs) {
        this.maxRetries = maxRetries;
        this.waitTimeMs = waitTimeMs;
    }

    @SneakyThrows
    @Override
    public Boolean call(Callable<Boolean> function) {
        int retryCount = 0;

        while (maxRetries < 0 || retryCount < maxRetries) {
            if (function.call()) {
                return true;
            }

            retryCount++;
            Thread.sleep(waitTimeMs);
        }

        return false;
    }
}