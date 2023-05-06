CREATE TABLE owner (
    id      SERIAL PRIMARY KEY,
    name    VARCHAR NOT NULL,
    user_id int NOT NULL REFERENCES auto_user(id)
);