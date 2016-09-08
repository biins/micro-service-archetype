package ${package}.core.service;

public class BasicService {

    public static class Message {

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

    public Message sayHello(String name, String country) {
        return new Message(String.format("Hello %s from %s", name, country));
    }

}