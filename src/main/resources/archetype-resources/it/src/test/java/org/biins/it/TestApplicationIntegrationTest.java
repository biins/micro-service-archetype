package ${package}.it;

import ${package}.app.TestApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "/application-test.properties")
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class TestApplicationIntegrationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setupPort() {
        RestAssured.port = port;
    }
}
