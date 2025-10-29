CREATE TABLE cars (
      id SERIAL PRIMARY KEY,
      name varchar not null,
      engine_id INT NOT NULL REFERENCES engines(id)
);