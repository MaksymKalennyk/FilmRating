CREATE TABLE movies (
                        id BIGINT(20) NOT NULL AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        source VARCHAR(255) NOT NULL,
                        genre VARCHAR(255) NOT NULL,
                        release_date DATE NOT NULL,
                        PRIMARY KEY (id)
);
