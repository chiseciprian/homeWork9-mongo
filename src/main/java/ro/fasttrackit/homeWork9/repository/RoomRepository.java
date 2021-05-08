package ro.fasttrackit.homeWork9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.homeWork9.model.entity.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    boolean existsByNumber(String number);
}
