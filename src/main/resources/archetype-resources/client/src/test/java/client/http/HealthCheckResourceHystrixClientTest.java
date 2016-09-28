package ${package}.client.http;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.apache.http.HttpStatus;
import ${package}.client.api.test.Message;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.TestFeignClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCommand;

import feign.FeignException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import rx.Single;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HealthCheckResourceHystrixClientTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String HELLO = "Hello";
    private static final String NAME = "Martin";
    private static final String ORIGIN = "Space";
    private static final Message MESSAGE = new Message(HELLO);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private MockWebServer mockWebServer;
    @Autowired
    private HealthCheckResourceHystrixClient client;

    @Test
    public void testSayHelloOkResponse() throws JsonProcessingException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(OBJECT_MAPPER.writeValueAsString(MESSAGE))
                .setResponseCode(HttpStatus.SC_OK));

        Optional<Message> message = client.sayHello(NAME, ORIGIN);

        assertThat(message.isPresent(), is(true));
        assertThat(message.get().getMessage(), is(HELLO));
    }

    @Test
    public void testSayHelloNotFoundResponse() throws JsonProcessingException {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_NOT_FOUND));

        Optional<Message> message = client.sayHello(NAME, ORIGIN);

        assertThat(message.isPresent(), is(false));
    }

    @Test
    public void testSayHelloServiceUnavailableResponse() throws JsonProcessingException {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_SERVICE_UNAVAILABLE));

        Optional<Message> message = client.sayHello(NAME, ORIGIN);

        assertThat(message.isPresent(), is(false));
    }

    @Test
    public void testSayHelloToRxOkResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(OBJECT_MAPPER.writeValueAsString(MESSAGE))
                .setResponseCode(HttpStatus.SC_OK));

        Single<Message> message = client.sayHelloToRx(NAME, ORIGIN);

        assertThat(message.toBlocking().value().getMessage(), is(HELLO));
    }

    @Test
    public void testSayHelloToRxNotFoundResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_NOT_FOUND));

        Single<Message> message = client.sayHelloToRx(NAME, ORIGIN);

        assertThat(message.toBlocking().value(), is(nullValue()));
    }

    @Test
    public void testSayHelloToRxServiceUnavailableResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_SERVICE_UNAVAILABLE));

        Single<Message> message = client.sayHelloToRx(NAME, ORIGIN);

        assertThat(message.toBlocking().value(), is(nullValue()));
    }
    @Test
    public void testSayHelloToHystrixCommandOkResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(OBJECT_MAPPER.writeValueAsString(MESSAGE))
                .setResponseCode(HttpStatus.SC_OK));

        HystrixCommand<Message> message = client.sayHelloToHystrixCommand(NAME, ORIGIN);

        assertThat(message.execute().getMessage(), is(HELLO));
        assertThat(message.isSuccessfulExecution(), is(true));
    }

    @Test
    public void testSayHelloToHystrixCommandNotFoundResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_NOT_FOUND));

        HystrixCommand<Message> message = client.sayHelloToHystrixCommand(NAME, ORIGIN);

        assertThat(message.execute(), is(nullValue()));
        assertThat(message.isSuccessfulExecution(), is(true));
    }

    @Test
    public void testSayHelloToHystrixCommandServiceUnavailableResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_SERVICE_UNAVAILABLE));

        HystrixCommand<Message> message = client.sayHelloToHystrixCommand(NAME, ORIGIN);

        assertThat(message.execute(), is(nullValue()));
        assertThat(message.isSuccessfulExecution(), is(false));
    }

    @Configuration
    public static class Config extends MockServerClientConfig<HealthCheckResourceHystrixClient> {

        protected Config() {
            super(HealthCheckResourceHystrixClient.class, HealthCheckResourceHystrixClientConfig.class);
        }
    }
}