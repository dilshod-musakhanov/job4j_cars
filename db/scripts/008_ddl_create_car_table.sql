CREATE TABLE car (
    id        SERIAL  PRIMARY KEY,
    name      VARCHAR NOT NULL,
    engine_id int     NOT NULL REFERENCES engine (id),
    owner_id  int     NOT NULL REFERENCES owner (id)
);