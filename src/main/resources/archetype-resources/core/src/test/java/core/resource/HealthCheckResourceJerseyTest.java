package ${package}.core.resource;

import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;
import static javax.ws.rs.core.Response.Status.Family.familyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.biins.commons.jersey.JacksonMapperProvider;
import org.biins.commons.test.ResourceTest;
import ${package}.core.dto.Message;
import ${package}.core.service.GreetingService;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.metrics.CounterService;

public class HealthCheckResourceJerseyTest extends ResourceTest {

    private static final String ORIGIN = "System";
    private static final String NAME = "Martin";
    private static final String GREETING = "Hello";

    @Mock
    private CounterService counterService;
    @Mock
    private GreetingService greetingService;

    @Override
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(greetingService.sayHello(anyString(), anyString()))
                .thenReturn(new Message(GREETING));

        register(JacksonMapperProvider.class);
        register(JacksonFeature.class);
        register(new HealthCheckResource(counterService, greetingService));
    }

    @Test
    public void testHello() {
        Response response = target(HealthCheckResource.HEALTH_CHECK_PATH)
                .queryParam("name", NAME)
                .request(MediaType.APPLICATION_JSON)
                .header("origin", ORIGIN)
                .get(Response.class);

        assertThat(familyOf(response.getStatus()), is(SUCCESSFUL));
        assertThat(response.readEntity(Message.class).getMessage(), is(GREETING));
        verify(greetingService).sayHello(NAME, ORIGIN);
    }
}