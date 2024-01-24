package com.example.filmrating.controller;

import com.example.filmrating.model.Reviews;
import com.example.filmrating.model.ViewedMovie;
import com.example.filmrating.model.dto.ViewedMovieRequest;
import com.example.filmrating.service.ViewedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/friends")
public class ViewedMovieController {

    private final ViewedMovieService viewedMovieService;

    @Autowired
    public ViewedMovieController(ViewedMovieService viewedMovieService) {
        this.viewedMovieService = viewedMovieService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<Reviews> getFriendReviewOnMovie(@RequestBody ViewedMovieRequest viewedMovieRequest) {
        Reviews review = viewedMovieService.getFriendReviewOnMovie(viewedMovieRequest.getUserId(), viewedMovieRequest.getMovieId());
        return review != null ? ResponseEntity.ok(review)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/viewed-movies")
    public ResponseEntity<List<ViewedMovie>> getViewedMoviesWithReviewsByUser(@RequestBody ViewedMovieRequest viewedMovieRequest) {
        List<ViewedMovie> movies = viewedMovieService.getViewedMoviesWithReviewsByFriends(viewedMovieRequest.getUserId());
        return ResponseEntity.ok(movies);
    }
}
