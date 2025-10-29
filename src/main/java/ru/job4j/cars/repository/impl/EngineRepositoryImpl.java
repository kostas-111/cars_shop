package ru.job4j.cars.repository.impl;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.EngineRepository;

@Repository
@RequiredArgsConstructor
public class EngineRepositoryImpl implements EngineRepository {

  private final CrudRepository crudRepository;

  @Override
  public Engine create(Engine engine) {
    crudRepository.run(session -> session.persist(engine));
    return engine;
  }

  @Override
  public void update(Engine engine) {
    crudRepository.run(session -> session.merge(engine));
  }

  @Override
  public void delete(int engineId) {
    crudRepository.run(
        "DELETE FROM Engine WHERE id = :fId",
        Map.of("fId", engineId)
    );
  }

  @Override
  public Optional<Engine> findById(int engineId) {
    return crudRepository.optional(
        "FROM Engine WHERE id = :fId", Engine.class,
        Map.of("fId", engineId)
    );
  }

  @Override
  public Optional<Engine> findByName(String name) {
    return crudRepository.optional(
        "FROM Engine WHERE name = :fName", Engine.class,
        Map.of("fName", name)
    );
  }
}
