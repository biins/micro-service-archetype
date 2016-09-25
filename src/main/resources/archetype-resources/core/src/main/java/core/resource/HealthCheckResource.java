package ${package}.core.resource;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.biins.commons.logging.Logger;
import org.biins.commons.logging.LoggerFactory;
import ${package}.core.dto.Message;
import ${package}.core.service.GreetingService;
import org.springframework.boot.actuate.metrics.CounterService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(HealthCheckResource.HEALTH_CHECK_PATH)
public class HealthCheckResource {

    private static final String HEALTH_CHECK_COUNTER = HealthCheckResource.class.getName() + ".request.count";
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckResource.class);

    public static final String HEALTH_CHECK_PATH = "/";

    private static final String HEADER_ORIGIN = "origin";
    private static final String HEADER_ORIGIN_DEFAULT = "Matrix";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_NAME_DEFAULT = "stranger";

    private final CounterService counterService;
    private final GreetingService greetingService;

    @Context
    private HttpHeaders httpHeaders;
    @Context
    private UriInfo uriInfo;

    @Inject
    public HealthCheckResource(CounterService counterService, GreetingService greetingService) {
        this.counterService = counterService;
        this.greetingService = greetingService;
    }

    @GET
    public Response hello(@QueryParam(PARAM_NAME) @DefaultValue(PARAM_NAME_DEFAULT) String name,
                          @HeaderParam(HEADER_ORIGIN) String header)    {
        // TODO: change
        counterService.increment(HEALTH_CHECK_COUNTER);
        LOGGER.debug("Incoming request '{}'", uriInfo.getRequestUri());

        String origin = Optional.ofNullable(httpHeaders.getHeaderString(HEADER_ORIGIN)).orElse(HEADER_ORIGIN_DEFAULT);
        Message message = greetingService.sayHello(name, origin);
        LOGGER.asJson().debug("Message '{}'", message);
        return Response.ok(message).build();
    }
}