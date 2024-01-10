package com.example.filmrating.repo;

import com.example.filmrating.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findByMoviesId(Long movieId);
}
