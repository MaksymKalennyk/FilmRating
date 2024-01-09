package com.example.filmrating.repo;

import com.example.filmrating.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movies, Long> {
    Optional<Movies> findByName(String name);
}
