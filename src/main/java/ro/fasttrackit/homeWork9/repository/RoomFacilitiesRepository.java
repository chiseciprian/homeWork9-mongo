package ro.fasttrackit.homeWork9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.homeWork9.model.entity.RoomFacilities;

public interface RoomFacilitiesRepository extends MongoRepository<RoomFacilities, String> {
}
