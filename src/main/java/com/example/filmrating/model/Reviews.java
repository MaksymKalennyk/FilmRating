package com.example.filmrating.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "movieId", referencedColumnName = "id")
    private Movies movies;

    private int rating;
    private String reviewText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    private int like;
}
