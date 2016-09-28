package ${package}.client.http;

import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClientSpecification;
import org.springframework.cloud.netflix.feign.TestFeignClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import okhttp3.mockwebserver.MockWebServer;

@Configuration
@Import({
        FeignAutoConfiguration.class
})
public abstract class MockServerClientConfig<T> {

    private final Class<T> clientClass;
    private final Class<?> clientConfiguration;

    protected MockServerClientConfig(Class<T> clientClass, Class<?> clientConfiguration) {
        this.clientClass = clientClass;
        this.clientConfiguration = clientConfiguration;
    }

    @Bean
    public MockWebServer mockWebServer() {
        return new MockWebServer();
    }

    @Bean
    public TestFeignClientFactoryBean<T> feignClientFactoryBean(MockWebServer mockWebServer) {
        TestFeignClientFactoryBean<T> feignClientFactoryBean = new TestFeignClientFactoryBean<>(clientClass);
        feignClientFactoryBean.setName(mockWebServer.url("/").toString());
        return feignClientFactoryBean;
    }

    @Bean
    public FeignClientSpecification feignClientSpecification(TestFeignClientFactoryBean testFeignClientFactoryBean) {
        return new FeignClientSpecification(testFeignClientFactoryBean.getName(), new Class[]{clientConfiguration});
    }
}
