package ro.fasttrackit.homeWork9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.homeWork9.model.entity.Cleanup;

import java.util.List;

@Repository
public interface CleanupRepository extends MongoRepository<Cleanup, String> {

    List<Cleanup> findCleanupsByRoomId(String roomId);
}
