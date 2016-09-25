package ${package}.core.db;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.fakemongo.Fongo;
import com.lordofthejars.nosqlunit.mongodb.MongoDbConfiguration;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.lordofthejars.nosqlunit.mongodb.MongoOperation;
import com.mongodb.Mongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public abstract class RepositoryTest {

    private static final String DB_NAME = "test";

    @Rule
    public MongoDbRule mongoDbRule = new SpringMongoDbRule(mongoDb().databaseName(DB_NAME).build());

    @Inject
    private ApplicationContext applicationContext;

    @Configuration
    @EnableMongoRepositories(
            basePackages = "${package}.core.db",
            repositoryImplementationPostfix = "CustomImpl")
    static class TestRepositoryConfiguration extends AbstractMongoConfiguration {

        private final Mongo mongo = new Fongo(DB_NAME).getMongo();

        @Override
        protected String getDatabaseName() {
            return DB_NAME;
        }

        @Override
        public Mongo mongo() {
            return mongo;
        }

        @Override
        protected String getMappingBasePackage() {
            return "${package}.core.domain";
        }

        public Mongo getMongo() {
            return mongo;
        }
    }

    private class SpringMongoDbRule extends MongoDbRule {

        private MongoDbConfiguration mongoDbConfiguration;

        public SpringMongoDbRule(MongoDbConfiguration mongoDbConfiguration) {
            super(mongoDbConfiguration);
            this.mongoDbConfiguration = mongoDbConfiguration;
        }

        @Override
        public Statement apply(Statement base, FrameworkMethod method, Object testObject) {
            this.mongoDbConfiguration.setMongo(getMongo());
            this.databaseOperation = new MongoOperation(this.mongoDbConfiguration);
            return super.apply(base, method, testObject);
        }

    }

    private Mongo getMongo() {
        return applicationContext.getBean(TestRepositoryConfiguration.class).getMongo();
    }
}
