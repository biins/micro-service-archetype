package ${package}.app.config.job;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.PeriodicTrigger;

// TODO: remove
@Configuration
@ConditionalOnProperty(value = "task.test.enabled", havingValue = "true")
public class TestTaskConfig {

    @Bean
    public TestTask triggerTask() {
        return new TestTask();
    }

    public static class TestTask extends TriggerTask {

        public TestTask() {
            super(() -> System.out.println("Ping"), new PeriodicTrigger(5, TimeUnit.SECONDS));
        }
    }
}
