package ${package}.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
		ConversionServiceConfig.class
})
@ComponentScan(basePackages = "${package}.core")
public class CoreConfig {
}