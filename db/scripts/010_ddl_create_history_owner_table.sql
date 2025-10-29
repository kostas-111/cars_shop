create table history_owner(
                  id SERIAL PRIMARY KEY,
                  owner_id INT NOT NULL REFERENCES owners(id),
                  car_id INT NOT NULL REFERENCES cars(id),
                  start_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
                  end_at TIMESTAMP WITHOUT TIME ZONE
);