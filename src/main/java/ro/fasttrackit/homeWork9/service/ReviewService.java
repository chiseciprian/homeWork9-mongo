package ro.fasttrackit.homeWork9.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.homeWork9.controller.exception.EntityNotFoundException;
import ro.fasttrackit.homeWork9.controller.exception.ValidationException;
import ro.fasttrackit.homeWork9.model.entity.Review;
import ro.fasttrackit.homeWork9.repository.ReviewRepository;
import ro.fasttrackit.homeWork9.service.validator.ReviewValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewValidator validator;
    private final ObjectMapper mapper;

    public List<Review> getCleanups(String roomId) {
        return reviewRepository.findAllByRoomId(roomId);
    }

    public Review addReview(Review review) {
        validator.validateRoomExistsOrThrow(review.getRoomId());
        return reviewRepository.save(review);
    }

    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @SneakyThrows
    public Review patchReview(String reviewId, JsonPatch patch) {
        validator.validateExistsOrThrow(reviewId);
        Review dbReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find review with id " + reviewId));

        JsonNode patchedReviewJson = patch.apply(mapper.valueToTree(dbReview));
        Review patchedReview = mapper.treeToValue(patchedReviewJson, Review.class);
        return replaceCleanup(reviewId, patchedReview);
    }

    private Review replaceCleanup(String reviewId, Review newReview) {
        newReview.setReviewId(reviewId);
        validator.validateReplaceThrow(reviewId, newReview);

        Review dbReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ValidationException("Couldn't find review with id " + reviewId));
        copyProduct(newReview, dbReview);
        return reviewRepository.save(dbReview);
    }

    private void copyProduct(Review newReview, Review dbReview) {
        dbReview.setMessage(newReview.getMessage());
        dbReview.setRating(newReview.getRating());
        dbReview.setTourist(newReview.getTourist());
        dbReview.setRoomId(newReview.getRoomId());
    }
}
