CREATE TABLE viewed_movies (
                         id BIGINT(20) NOT NULL AUTO_INCREMENT,
                         user_id BIGINT(20) NOT NULL,
                         movie_id BIGINT(20) NOT NULL,
                         review_id BIGINT(20) NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (movie_id) REFERENCES movies(id),
                         FOREIGN KEY (review_id) REFERENCES reviews(id)
);
