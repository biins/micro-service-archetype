package ${package}.core.db;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import ${package}.core.domain.Greeting;
import org.junit.Test;
import org.springframework.data.domain.Example;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;

// TODO: remove
public class GreetingRepositoryTest extends RepositoryTest {

    private static final String WHOM = "Martin";
    private static final String WHENCE = "Home";

    @Inject
    private GreetingRepository greetingRepository;

    private Greeting greeting = new Greeting(WHOM, WHENCE);

    @Test
    @UsingDataSet(locations = "/db/greeting_empty.json")
    // @ShouldMatchDataSet(location = "/db/greeting.json")
    public void testInsertNewLog() {
        greetingRepository.logGreeting(greeting);

        Greeting savedGreeting = greetingRepository.findOne(Example.of(greeting));
        assertThat(savedGreeting.getWhom(), is(WHOM));
        assertThat(savedGreeting.getWhence(), is(WHENCE));
        assertThat(savedGreeting.getCount(), is(1L));
    }

    @Test
    @UsingDataSet(locations = "/db/greeting.json")
    public void testIncrementLog() {
        greetingRepository.logGreeting(greeting);

        Greeting savedGreeting = greetingRepository.findOne("hello");
        assertThat(savedGreeting.getWhom(), is(WHOM));
        assertThat(savedGreeting.getWhence(), is(WHENCE));
        assertThat(savedGreeting.getCount(), is(2L));
    }
}