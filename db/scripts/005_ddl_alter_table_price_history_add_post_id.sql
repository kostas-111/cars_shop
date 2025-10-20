ALTER TABLE price_history
    ADD COLUMN post_id INT NOT NULL REFERENCES auto_post(id);