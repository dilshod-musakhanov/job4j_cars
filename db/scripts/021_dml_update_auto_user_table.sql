ALTER TABLE auto_user
ADD COLUMN name varchar NOT NULL;

ALTER TABLE auto_user
RENAME COLUMN login TO email;