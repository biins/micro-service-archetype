package ${package}.core.db;

import java.util.Optional;
import java.util.stream.Stream;

import ${package}.core.domain.Greeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

// TODO: remove
public interface GreetingRepository extends GreetingRepositoryCustom, MongoRepository<Greeting, String> {

    Optional<Greeting> findById(String id);

    Slice<Greeting> findByWhom(String whom, Pageable pageable);

    Page<Greeting> findByWhence(String whence, Pageable pageable);

    @Query("{count: {$gt: ?0}}")
    Stream<Greeting> findByCountGreaterThan(int limit);
}
