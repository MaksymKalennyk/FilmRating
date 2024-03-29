CREATE TABLE users (
                       id BIGINT(20) NOT NULL AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id),
                       UNIQUE KEY unique_username (username),
                       UNIQUE KEY unique_email (email)
);
