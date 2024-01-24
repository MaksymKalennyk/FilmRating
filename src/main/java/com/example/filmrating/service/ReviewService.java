package com.example.filmrating.service;

import com.example.filmrating.model.Movies;
import com.example.filmrating.model.ReviewUserLike;
import com.example.filmrating.model.Reviews;
import com.example.filmrating.model.Users;
import com.example.filmrating.model.dto.ReviewRequest;
import com.example.filmrating.repo.MovieRepository;
import com.example.filmrating.repo.ReviewRepository;
import com.example.filmrating.repo.ReviewUserLikeRepository;
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
    private final ReviewUserLikeRepository reviewUserLikeRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository,
                         MovieRepository movieRepository, ReviewUserLikeRepository reviewUserLikeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reviewUserLikeRepository = reviewUserLikeRepository;
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

    public Reviews postLikes(Long reviewId, Long userId) {
        if (!reviewUserLikeRepository.existsByUsers_IdAndReviews_Id(userId, reviewId)) {
            Optional<Reviews> reviewsOptional = reviewRepository.findById(reviewId);
            if (reviewsOptional.isPresent()) {
                Reviews reviews = reviewsOptional.get();
                reviews.setLikeCount(reviews.getLikeCount() + 1);

                ReviewUserLike reviewUserLike = new ReviewUserLike();
                reviewUserLike.setUsers(userRepository.getReferenceById(userId));
                reviewUserLike.setReviews(reviewRepository.getReferenceById(reviewId));

                reviewUserLikeRepository.save(reviewUserLike);
                return reviewRepository.save(reviews);
            }
        }
        return null;
    }
}
