
CREATE TABLE genres (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);


CREATE TABLE authors (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE t_book (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        genre_id BIGINT,
                        CONSTRAINT fk_book_genre FOREIGN KEY (genre_id) REFERENCES genres (id)
);


CREATE TABLE book_authors (
                              book_id BIGINT NOT NULL,
                              author_id BIGINT NOT NULL,
                              PRIMARY KEY (book_id, author_id),
                              CONSTRAINT fk_ba_book FOREIGN KEY (book_id) REFERENCES t_book (id) ON DELETE CASCADE,
                              CONSTRAINT fk_ba_author FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);