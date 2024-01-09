package com.example.filmrating.service;

import com.example.filmrating.model.Movies;
import com.example.filmrating.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Optional<Movies> findMovieByName(String name){
        return movieRepository.findByName(name);
    }
}
