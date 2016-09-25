package ${package}.core.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: remove
@Document(collection = "greeting")
public class Greeting {

    @Id
    private String id;
    private String whom;
    private String whence;
    private long count;

    private Greeting() {
    }

    public Greeting(String whom, String whence) {
        this.id = UUID.randomUUID().toString();
        this.whom = whom;
        this.whence = whence;
        this.count = 1;
    }

    public String getId() {
        return id;
    }

    public String getWhom() {
        return whom;
    }

    public String getWhence() {
        return whence;
    }

    public long getCount() {
        return count;
    }
}
