package ${package}.app.config.cache;

import java.time.Duration;

public abstract class BaseCacheConfig {

    private Duration expire;
    private long size;

    public Duration getExpire() {
        return expire;
    }

    public void setExpire(Duration expireMin) {
        this.expire = expireMin;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
