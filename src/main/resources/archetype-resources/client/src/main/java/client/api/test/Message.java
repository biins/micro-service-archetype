package ${package}.client.api.test;

public class Message {

    private String message;

    private Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
