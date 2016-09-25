package ${package}.app.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "${package}.core.db",
        repositoryImplementationPostfix = "CustomImpl"
)
public class RepositoryConfig {

}
