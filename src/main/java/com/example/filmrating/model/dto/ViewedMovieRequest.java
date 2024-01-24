package com.example.filmrating.model.dto;

import lombok.Data;

@Data
public class ViewedMovieRequest {

    private Long userId;
    private Long movieId;
}
