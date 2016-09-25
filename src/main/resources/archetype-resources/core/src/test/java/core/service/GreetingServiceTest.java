package ${package}.core.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ${package}.core.db.GreetingRepository;
import ${package}.core.domain.Greeting;

// TOOD: remove
public class GreetingServiceTest {

    @Mock
    private GreetingRepository greetingRepository;

    private GreetingService greetingService;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        greetingService = new GreetingService(greetingRepository);
    }

    @Test
    public void sayHello() throws Exception {
        assertThat(greetingService.sayHello("A", "B").getMessage(), is("Hello A from B"));
        verify(greetingRepository).logGreeting(any(Greeting.class));
    }

}