package com.example.filmrating.service;

import com.example.filmrating.model.ViewedMovie;
import com.example.filmrating.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewedMovieService {

    private final ViewedMovieRepository viewedMovieRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final FriendRepository friendRepository;
    private final MovieRepository movieRepository;



    public void addViewedMovie(Long userId, Long movieId, Long reviewId) {
        ViewedMovie viewedMovie = new ViewedMovie();

        viewedMovie.setUsers(userRepository.getReferenceById(userId));
        viewedMovie.setMovies(movieRepository.getReferenceById(movieId));
        viewedMovie.setReviews(reviewRepository.getReferenceById(reviewId));

        viewedMovieRepository.save(viewedMovie);
    }

    public List<ViewedMovie> getViewedMoviesWithReviewsByFriends(Long userId) {
        List<Long> friendIds = friendRepository.findAllByUsersId(userId).stream()
                .map(friend -> friend.getFriends().getId()).toList();

        List<ViewedMovie> viewedMoviesWithReviews = new ArrayList<>();
        for (Long friendId : friendIds) {
            List<ViewedMovie> friendViewedMovies = viewedMovieRepository
                    .findAllByUsersIdAndReviewsIsNotNull(friendId);
            viewedMoviesWithReviews.addAll(friendViewedMovies);
        }
        return viewedMoviesWithReviews;
    }
}
