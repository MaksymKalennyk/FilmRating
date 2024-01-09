package com.example.filmrating.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Review request")
public class ReviewRequest {

    private long userId;
    private long movieId;
    private int rating;
    private String reviewText;
    private Date reviewDate;
}
