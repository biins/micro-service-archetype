package ${package}.app.config.db;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

import javax.inject.Inject;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    private final MongoProperties mongoProperties;

    @Inject
    public MongoConfig(MongoProperties mongoProperties) {
        this.mongoProperties = mongoProperties;
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Override
    protected String getMappingBasePackage() {
        return "${package}.core.domain";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoProperties.getHost(), mongoProperties.getPort());
    }

}
