package ru.job4j.cars.repository;

import java.util.Optional;
import ru.job4j.cars.model.Engine;

public interface EngineRepository {

  Engine create(Engine engine);

  void update(Engine engine);

  void delete(int engineId);

  Optional<Engine> findById(int engineId);

  Optional<Engine> findByName(String name);
}
