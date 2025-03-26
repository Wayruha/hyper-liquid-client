package trade.wayruha.hyperliquid.websocket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class ExponentialRetryReconnectStrategy implements RetryStrategy<Boolean, Boolean> {
    private final int maxRetries;
    private final long initialWaitTime;
    private final long maxWaitTime;

    public ExponentialRetryReconnectStrategy(int maxRetries, long initialWaitTimeMs, long maxWaitTimeMs) {
        this.maxRetries = maxRetries;
        this.initialWaitTime = initialWaitTimeMs;
        this.maxWaitTime = maxWaitTimeMs;
    }

    @SneakyThrows
    @Override
    public Boolean call(Callable<Boolean> function) {
        int retryCount = 0;
        long waitTime = initialWaitTime;

        while (maxRetries < 0 || retryCount < maxRetries) {
            if (function.call()) {
                return true;
            }

            retryCount++;
            Thread.sleep(waitTime);
            waitTime = Math.min(waitTime * 2, maxWaitTime);
        }

        return false;
    }
}