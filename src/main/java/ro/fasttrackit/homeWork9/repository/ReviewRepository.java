package ro.fasttrackit.homeWork9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.homeWork9.model.entity.Review;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findAllByRoomId(String roomId);
}
