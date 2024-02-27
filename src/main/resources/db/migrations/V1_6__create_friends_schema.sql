CREATE TABLE friends (
                         id BIGINT(20) NOT NULL AUTO_INCREMENT,
                         user_id BIGINT(20) NOT NULL,
                         friend_id BIGINT(20) NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (friend_id) REFERENCES users(id),
                         UNIQUE KEY unique_user_friend_pair (user_id, friend_id)
);
