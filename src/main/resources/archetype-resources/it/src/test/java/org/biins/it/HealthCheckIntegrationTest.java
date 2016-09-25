package ${package}.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class HealthCheckIntegrationTest extends TestApplicationIntegrationTest {

	@Test
	public void testHealthCheck() {
		given()
			.basePath("/test/")
		.then()
			.request()
			.get()
		.then()
			.statusCode(is(HttpStatus.SC_OK))
			.body("message", startsWith("Hello"));
	}
}
