package ${package}.core.service;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class BasicServiceTest {

    private BasicService basicService;

    @Before
    public void setUp() throws Exception {
        basicService = new BasicService();
    }

    @Test
    public void sayHello() throws Exception {
        assertThat(basicService.sayHello("A", "B").getMessage(), Matchers.is("Hello A from B"));
    }

}