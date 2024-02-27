CREATE TABLE review_user_like (
                                  id BIGINT(20) NOT NULL AUTO_INCREMENT,
                                  user_id BIGINT(20) NOT NULL,
                                  review_id BIGINT(20) NOT NULL,
                                  PRIMARY KEY (id),
                                  FOREIGN KEY (user_id) REFERENCES users(id),
                                  FOREIGN KEY (review_id) REFERENCES reviews(id),
                                  UNIQUE KEY unique_user_review (user_id, review_id)
);
