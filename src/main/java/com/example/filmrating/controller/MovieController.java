package com.example.filmrating.controller;

import com.example.filmrating.model.Movies;
import com.example.filmrating.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies/search")
    public Optional<Movies> findByName(@RequestParam("name") String name){
        return movieService.findMovieByName(name);
    }
}
