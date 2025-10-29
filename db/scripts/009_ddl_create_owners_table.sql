CREATE TABLE owners (
          id SERIAL PRIMARY KEY,
          name varchar not null,
          user_id INT NOT NULL REFERENCES auto_user(id)
);