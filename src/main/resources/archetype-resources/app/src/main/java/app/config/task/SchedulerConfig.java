package ${package}.app.config.job;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;

@Configuration
@EnableScheduling
@EnableConfigurationProperties(SchedulerProperties.class)
@Import({
		TestTaskConfig.class
})
@ConditionalOnBean(Task.class)
public class SchedulerConfig implements SchedulingConfigurer {

	private final SchedulerProperties schedulerProperties;
	private final Optional<TestTaskConfig.TestTask> testTask;

	@Inject
	public SchedulerConfig(SchedulerProperties schedulerProperties, Optional<TestTaskConfig.TestTask> testTask) {
		this.schedulerProperties = schedulerProperties;
		this.testTask = testTask;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(executor());
		testTask.ifPresent(taskRegistrar::addTriggerTask);
	}

	@Bean
	ThreadPoolTaskScheduler executor() {
		ThreadPoolTaskScheduler threadPoolTaskExecutor = new ThreadPoolTaskScheduler();
		threadPoolTaskExecutor.setPoolSize(schedulerProperties.getPoolSize());
		threadPoolTaskExecutor.setAwaitTerminationSeconds(Integer.MAX_VALUE);
		threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		return threadPoolTaskExecutor;
	}
}
