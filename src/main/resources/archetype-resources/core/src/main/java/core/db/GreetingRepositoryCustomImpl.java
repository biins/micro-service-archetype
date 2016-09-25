package ${package}.core.db;

import javax.validation.constraints.NotNull;

import ${package}.core.domain.Greeting;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

// TODO: remove
public class GreetingRepositoryCustomImpl implements GreetingRepositoryCustom {

    private static final String WHOM = "whom";
    private static final String WHENCE = "whence";
    private static final String COUNT = "count";

    private MongoTemplate mongoTemplate;

    public GreetingRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void logGreeting(@NotNull Greeting greeting) {
        Query query = Query.query(Criteria.where(WHOM).is(greeting.getWhom()).and(WHENCE).is(greeting.getWhence()));
        Update update = new Update().inc(COUNT, 1);

        boolean exists = mongoTemplate.updateFirst(query, update, Greeting.class).getN() > 0;
        if (!exists) {
            mongoTemplate.save(greeting);
        }
    }
}
