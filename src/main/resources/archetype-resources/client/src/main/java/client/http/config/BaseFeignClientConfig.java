package ${package}.client.http.config;

import java.util.List;

import org.biins.commons.feign.OptionalAwareDecoder;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public abstract class BaseFeignClientConfig {

    @Bean
    ApacheHttpClient feignClient() {
        return new ApacheHttpClient();
    }

    @Bean
    Module jdk8Module() {
        return new Jdk8Module();
    }

    @Bean
    Encoder feignEncoder(List<Module> modules) {
        return new JacksonEncoder(modules);
    }

    @Bean
    Decoder feignDecoder() {
        return new OptionalAwareDecoder(new JacksonDecoder());
    }

}
