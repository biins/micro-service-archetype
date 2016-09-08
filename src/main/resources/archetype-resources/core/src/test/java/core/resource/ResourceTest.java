package ${package}.core.resource;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

public abstract class ResourceTest {

    private JerseyTest jerseyTest;

    private List<Object> components = new LinkedList<>();

    protected abstract void setup();

    protected void register(Object component) {
        components.add(component);
    }

    @Before
    public void setUpJerseyTest() throws Exception {
        setup();

        this.jerseyTest = new JerseyTest() {
            @Override
            protected Application configure() {
                ResourceConfig resourceConfig = new ResourceConfig();
                resourceConfig.register(JacksonFeature.class);
                resourceConfig.property("contextConfigLocation", "classpath:jersey-spring-applicationContext.xml");
                components.forEach(resourceConfig::register);
                return resourceConfig;

            }
        };

        this.jerseyTest.setUp();
    }

    @After
    public void tearDownJerseyTest() throws Exception {
        this.jerseyTest.tearDown();
    }

    public WebTarget target() {
        return jerseyTest.target();
    }

    public WebTarget target(String path) {
        return jerseyTest.target(path);
    }

    public Client client() {
        return jerseyTest.client();
    }

    public void close(Response... responses) {
        jerseyTest.close(responses);
    }

    public static void closeIfNotNull(Client... clients) {
        JerseyTest.closeIfNotNull(clients);
    }
}
