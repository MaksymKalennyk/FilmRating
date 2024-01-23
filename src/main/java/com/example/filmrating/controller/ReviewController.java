package com.example.filmrating.controller;

import com.example.filmrating.model.Reviews;
import com.example.filmrating.model.Users;
import com.example.filmrating.model.dto.ReviewRequest;
import com.example.filmrating.service.ReviewService;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<Reviews> postReview(@RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok(reviewService.saveReviewFromRequest(reviewRequest));
    }

    @GetMapping("/reviews/movie/{movieId}")
    public ResponseEntity<List<Reviews>> getReviewById(@PathVariable Long movieId){
        List<Reviews> reviews = reviewService.getReviewsByMovieId(movieId);

        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Reviews> postLikes(@PathVariable Long id, @AuthenticationPrincipal Users users) {
        Reviews updatedReviews = reviewService.postLikes(id, users.getId());
        return (updatedReviews != null) ? ResponseEntity.ok(updatedReviews) : ResponseEntity.notFound().build();
    }
}
