package ${package}.core.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

// TODO: rename me
public class AnotherServiceHealthIndicator implements HealthIndicator {


    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    private int check() {
        // TODO: check service
        return 0;
    }
}
