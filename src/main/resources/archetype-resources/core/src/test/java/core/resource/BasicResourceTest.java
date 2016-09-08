package ${package}.core.resource;

import org.biins.core.service.BasicService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.Family.familyOf;
import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class BasicResourceTest extends ResourceTest {

    @Mock
    private BasicService basicService;

    @Override
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(basicService.sayHello(anyString(), anyString())).thenCallRealMethod();

        register(new BasicResource(basicService));
    }

    @Test
    public void testHello() {
        Response response = target(BasicResource.HELLO_PATH)
                .queryParam("name", "Martin")
                .request(MediaType.APPLICATION_JSON)
                .header("country", "Czechia")
                .get(Response.class);

        assertThat(familyOf(response.getStatus()), is(SUCCESSFUL));
        assertThat(response.readEntity(BasicService.Message.class).getMessage(), is("Hello Martin from Czechia"));
    }
}