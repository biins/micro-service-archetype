package ${package}.core.service;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

import ${package}.core.db.GreetingRepository;
import ${package}.core.domain.Greeting;
import ${package}.core.dto.Message;

// TODO: remove
@Named
@Singleton
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Message sayHello(@NotNull String name, @NotNull String country) {
        greetingRepository.logGreeting(new Greeting(name, country));
        return new Message(String.format("Hello %s from %s", name, country));
    }

}