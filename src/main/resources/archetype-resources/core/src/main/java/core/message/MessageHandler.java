package ${package}.core.message;

public interface MessageHandler<T> {

    void handleMessage(T message);

}
