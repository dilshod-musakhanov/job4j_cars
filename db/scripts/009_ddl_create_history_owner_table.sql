CREATE TABLE history_owner (
    id       SERIAL PRIMARY KEY,
    car_id   int NOT NULL REFERENCES car(id),
    owner_id int NOT NULL REFERENCES owner(id)
);