package com.example.filmrating.controller;

import com.example.filmrating.model.Reviews;
import com.example.filmrating.model.dto.ReviewRequest;
import com.example.filmrating.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<Reviews> postReview(@RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok(reviewService.saveReviewFromRequest(reviewRequest));
    }
}
