package com.example.filmrating.repo;

import com.example.filmrating.model.ViewedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewedMovieRepository extends JpaRepository<ViewedMovie, Long>{
    List<ViewedMovie> findAllByUsersId(Long userId);
    List<ViewedMovie> findAllByUsersIdAndReviewsIsNotNull(Long userId);
    Optional<ViewedMovie> findByUsersIdAndMoviesIdAndReviewsIsNotNull(Long userId, Long movieId);
}
