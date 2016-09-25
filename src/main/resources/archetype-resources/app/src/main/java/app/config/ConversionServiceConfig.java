package ${package}.app.config;

import java.util.Set;

import org.biins.commons.spring.converter.StringToDurationConverter;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConversionServiceConfig {

	@Bean
	public ConversionServiceFactoryBean conversionService(Set<Converter> converters) {
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		conversionServiceFactoryBean.setConverters(converters);
		return conversionServiceFactoryBean;
	}

	@Bean
	@ConfigurationPropertiesBinding
	public StringToDurationConverter stringToDurationConverter() {
		return new StringToDurationConverter();
	}
}
