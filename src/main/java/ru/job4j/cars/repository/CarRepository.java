package ru.job4j.cars.repository;

import java.util.Optional;
import ru.job4j.cars.model.Car;

public interface CarRepository {

  Car create(Car car);

  void update(Car car);

  void delete(int carId);

  Optional<Car> findById(int carId);

  Optional<Car> findByName(String name);
}
