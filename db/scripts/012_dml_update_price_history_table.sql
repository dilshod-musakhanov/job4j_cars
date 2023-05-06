ALTER TABLE price_history ADD COLUMN auto_post_id int REFERENCES auto_post(id);
