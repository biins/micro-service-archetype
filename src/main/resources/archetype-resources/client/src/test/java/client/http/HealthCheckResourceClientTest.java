package ${package}.client.http;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.apache.http.HttpStatus;
import ${package}.client.api.test.Message;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HealthCheckResourceClientTest {

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
    private HealthCheckResourceClient client;

    @Test
    public void testOkResponse() throws JsonProcessingException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(OBJECT_MAPPER.writeValueAsString(MESSAGE))
                .setResponseCode(HttpStatus.SC_OK));

        Optional<Message> message = client.sayHello(NAME, ORIGIN);

        assertThat(message.isPresent(), is(true));
        assertThat(message.get().getMessage(), is(HELLO));
    }

    @Test
    public void testNotFoundResponse() throws JsonProcessingException {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_NOT_FOUND));

        Optional<Message> message = client.sayHello(NAME, ORIGIN);

        assertThat(message.isPresent(), is(false));
    }

    @Test
    public void testServiceUnavailableResponse() throws JsonProcessingException {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.SC_SERVICE_UNAVAILABLE));

        thrown.expect(FeignException.class);
        thrown.expectMessage(containsString(String.valueOf(HttpStatus.SC_SERVICE_UNAVAILABLE)));

        Optional<Message> message = client.sayHello(NAME, ORIGIN);

        assertThat(message.isPresent(), is(true));
    }

    @Configuration
    public static class Config extends MockServerClientConfig<HealthCheckResourceClient> {

        public Config() {
            super(HealthCheckResourceClient.class, HealthCheckResourceClientConfig.class);
        }
    }
}