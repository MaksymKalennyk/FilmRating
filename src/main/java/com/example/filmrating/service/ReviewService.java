package com.example.filmrating.service;

import com.example.filmrating.model.Movies;
import com.example.filmrating.model.Reviews;
import com.example.filmrating.model.Role;
import com.example.filmrating.model.Users;
import com.example.filmrating.model.dto.ReviewRequest;
import com.example.filmrating.repo.MovieRepository;
import com.example.filmrating.repo.ReviewRepository;
import com.example.filmrating.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public Reviews saveReviewFromRequest(ReviewRequest request) {
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Movies movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        Reviews review = new Reviews();
        review.setRating(request.getRating());
        review.setReviewText(request.getReviewText());
        review.setReviewDate(request.getReviewDate());
        review.setUsers(user);
        review.setMovies(movie);

        return reviewRepository.save(review);
    }

    public List<Reviews> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findByMoviesId(movieId);
    }
}
