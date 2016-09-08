package ${package}.core.resource;

import org.biins.core.service.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(BasicResource.HELLO_PATH)
public class BasicResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicResource.class);

    public static final String HELLO_PATH = "/";

    private static final String HEADER_COUNTRY = "country";
    private static final String HEADER_COUNTRY_DEFAULT = "Earth";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_NAME_DEFAULT = "stranger";

    private final BasicService basicService;

    @Context
    private HttpHeaders httpHeaders;
    @Context
    private UriInfo uriInfo;

    @Inject
    public BasicResource(BasicService basicService) {
        this.basicService = basicService;
    }

    @GET
    public Response hello(@QueryParam(PARAM_NAME) @DefaultValue(PARAM_NAME_DEFAULT) String name) {
        LOGGER.debug("Incoming request '{}'", uriInfo.getRequestUri());

        String country = Optional.ofNullable(httpHeaders.getHeaderString(HEADER_COUNTRY)).orElse(HEADER_COUNTRY_DEFAULT);
        return Response.ok(basicService.sayHello(name, country)).build();
    }
}