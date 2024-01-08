package com.example.filmrating.repo;

import com.example.filmrating.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movies, Long> {
}
