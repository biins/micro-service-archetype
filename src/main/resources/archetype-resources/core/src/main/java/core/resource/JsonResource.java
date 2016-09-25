package ${package}.core.resource;

import static com.monitorjbl.json.Match.match;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.monitorjbl.json.JsonView;

// TODO: remove
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path(JsonResource.JSON_RESOURCE_PATH)
public class JsonResource {

    public static final String JSON_RESOURCE_PATH = "/json";

    @GET
    public Response hello() {
        Person person = new Person("1", "Bob", "Smith");
        // example of excluded field
        JsonView<Person> view = JsonView.with(person)
                .onClass(Person.class, match()
                    .exclude("*")
                    .include("firstName", "surname"));
        return Response.ok(view).build();
    }

    private static class Person {

        private final String id;
        private final String firstName;
        private final String surname;

        private Person(String id, String firstName, String surname) {
            this.id = id;
            this.firstName = firstName;
            this.surname = surname;
        }
    }
}