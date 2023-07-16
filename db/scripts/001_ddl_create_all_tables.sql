CREATE TABLE engine (
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE brand (
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE body (
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE fuel (
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE auto_user (
    id       SERIAL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    email    VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE owner (
    id        SERIAL PRIMARY KEY,
    name      VARCHAR NOT NULL,
    phone     VARCHAR NOT NULL,
    user_id   INT REFERENCES auto_user(id) NOT NULL
);

CREATE TABLE car (
    id              SERIAL PRIMARY KEY,
    name            VARCHAR NOT NULL,
    engine_id       INT REFERENCES engine(id) NOT NULL,
    owner_id        INT REFERENCES owner(id) NOT NULL,
    produced        INT NOT NULL,
    body_id         INT REFERENCES body(id) NOT NULL,
    brand_id        INT REFERENCES brand(id) NOT NULL,
    transmission    VARCHAR NOT NULL,
    fuel_id         INT REFERENCES fuel(id) NOT NULL
);

CREATE TABLE auto_post (
    id           SERIAL PRIMARY KEY,
    description  VARCHAR NOT NULL,
    created      TIMESTAMP NOT NULL,
    price        INT NOT NULL,
    car_new      BOOLEAN NOT NULL,
    car_sold     BOOLEAN NOT NULL,
    location     VARCHAR NOT NULL,
    mileage      INT NOT NULL,
    auto_user_id INT REFERENCES auto_user(id) NOT NULL,
    car_id       INT UNIQUE REFERENCES car(id) NOT NULL
);

CREATE TABLE price_history (
    id            SERIAL PRIMARY KEY,
    before        BIGINT NOT NULL,
    after         BIGINT NOT NULL,
    created       TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    auto_post_id  INT REFERENCES auto_post(id) NOT NULL
);

CREATE TABLE history_owner (
    id        SERIAL PRIMARY KEY,
    car_id    INT REFERENCES car(id) NOT NULL,
    owner_id  INT REFERENCES owner(id) NOT NULL
);

CREATE TABLE post_photo (
    id           SERIAL PRIMARY KEY,
    name         VARCHAR,
    path         VARCHAR NOT NULL UNIQUE,
    auto_post_id INT REFERENCES auto_post(id) NOT NULL
);

CREATE TABLE history (
    id      SERIAL PRIMARY KEY,
    startAt TIMESTAMP,
    endAt   TIMESTAMP
);

CREATE TABLE participates (
   id serial PRIMARY KEY,
   post_id int not null REFERENCES auto_post(id),
   user_id int not null REFERENCES auto_user(id)
);
