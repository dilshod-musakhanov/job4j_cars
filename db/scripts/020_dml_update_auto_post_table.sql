ALTER TABLE auto_post
ADD COLUMN price int NOT NULL,
ADD COLUMN car_new boolean NOT NULL,
ADD COLUMN car_sold boolean NOT NULL,
ADD COLUMN location varchar NOT NULL,
ADD COLUMN mileage int NOT NULL;