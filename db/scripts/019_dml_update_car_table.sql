ALTER TABLE car
ADD COLUMN produced int NOT NULL,
ADD COLUMN body_id int NOT NULL REFERENCES body(id),
ADD COLUMN brand_id int NOT NULL REFERENCES brand(id),
ADD COLUMN transmission_id int NOT NULL REFERENCES transmission(id),
ADD COLUMN fuel_id int NOT NULL REFERENCES fuel(id);