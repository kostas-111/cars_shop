package ru.job4j.cars.repository.impl;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CarRepository;
import ru.job4j.cars.repository.CrudRepository;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {

  private final CrudRepository crudRepository;

  @Override
  public Car create(Car car) {
    crudRepository.run(session -> session.persist(car));
    return car;
  }

  @Override
  public void update(Car car) {
    crudRepository.run(session -> session.merge(car));
  }

  @Override
  public void delete(int carId) {
    crudRepository.run(
        "DELETE FROM Car WHERE id = :fId",
        Map.of("fId", carId)
    );
  }

  @Override
  public Optional<Car> findById(int carId) {
    return crudRepository.optional(
        "FROM Car WHERE id = :fId", Car.class,
        Map.of("fId", carId)
    );
  }

  @Override
  public Optional<Car> findByName(String name) {
    return crudRepository.optional(
        "FROM Car WHERE name = :fName", Car.class,
        Map.of("fName", name)
    );
  }
}
