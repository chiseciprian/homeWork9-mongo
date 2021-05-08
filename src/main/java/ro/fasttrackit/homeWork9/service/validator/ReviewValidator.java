package ro.fasttrackit.homeWork9.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.homeWork9.controller.exception.ValidationException;
import ro.fasttrackit.homeWork9.model.entity.Review;
import ro.fasttrackit.homeWork9.repository.ReviewRepository;
import ro.fasttrackit.homeWork9.repository.RoomRepository;

import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class ReviewValidator {
    private final ReviewRepository reviewRepository;
    private final RoomRepository roomRepository;

    public void validateExistsOrThrow(String reviewId) {
        exists(reviewId).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> exists(String reviewId) {
        return reviewRepository.existsById(reviewId)
                ? empty()
                : Optional.of(new ValidationException("Review with id " + reviewId + " doesn't exist"));
    }

    public void validateRoomExistsOrThrow(String roomId) {
        existsRoom(roomId).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> existsRoom(String roomId) {
        return roomRepository.existsById(roomId)
                ? empty()
                : Optional.of(new ValidationException("Room with id " + roomId + " doesn't exist"));
    }

    public void validateReplaceThrow(String reviewId, Review newReview) {
        exists(reviewId)
                .or(() -> validate(newReview))
                .ifPresent(ex -> {
                    throw ex;
                });
    }

    private Optional<ValidationException> validate(Review review) {
        if (review.getTourist() == null) {
            return Optional.of(new ValidationException("Tourist cannot be null"));
        } else {
            return empty();
        }
    }
}
