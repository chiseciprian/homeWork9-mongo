package ro.fasttrackit.homeWork9.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homeWork9.model.entity.Review;
import ro.fasttrackit.homeWork9.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping(path = "{roomId}/reviews")
    List<Review> getReviews(@PathVariable String roomId) {
        return reviewService.getCleanups(roomId);
    }

    @PostMapping(path = "/review")
    Review addReview(@RequestBody @NonNull Review review) {
        return reviewService.addReview(review);
    }

    @PatchMapping("review/{reviewId}")
    Review patchReview(@RequestBody JsonPatch patch, @PathVariable String reviewId) {
        return reviewService.patchReview(reviewId, patch);
    }

    @DeleteMapping(path = "review/{reviewId}")
    void deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
