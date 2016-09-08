package ${package}.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ${serviceName}Application.class)
public class ${serviceName}ApplicationTest {

    @Inject
    private ApplicationContext applicationContext;

    @Test
    public void testApplicationContextStart() {
        assertThat(applicationContext.getStartupDate(), is(greaterThan(0L)));
    }
}
