CREATE TABLE post_photo (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    path VARCHAR NOT NULL UNIQUE,
    auto_post_id int REFERENCES auto_post(id)
);