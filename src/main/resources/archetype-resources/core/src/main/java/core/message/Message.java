package ${package}.core.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message<T> {

    private String uuid;
    private LocalDateTime timestamp;
    private T payload;

    private Message() {
    }

    public Message(T payload) {
        this.uuid = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.payload = payload;
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public T getPayload() {
        return payload;
    }
}
