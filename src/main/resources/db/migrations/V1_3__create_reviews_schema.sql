CREATE TABLE reviews (
                         id BIGINT(20) NOT NULL AUTO_INCREMENT,
                         user_id BIGINT(20) NOT NULL,
                         movie_id BIGINT(20) NOT NULL,
                         rating INT(11) NOT NULL,
                         review_text VARCHAR(1000),
                         review_date DATETIME NOT NULL,
                         like_count INT(11) NOT NULL DEFAULT 0,
                         PRIMARY KEY (id),
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (movie_id) REFERENCES movies(id)
);
