package ru.job4j.cars.repository.impl;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.OwnerRepository;

@Repository
@RequiredArgsConstructor
public class OwnerRepositoryImpl implements OwnerRepository {

  private final CrudRepository crudRepository;


  @Override
  public Owner create(Owner owner) {
    crudRepository.run(session -> session.save(owner));
    return owner;
  }

  @Override
  public void update(Owner owner) {
    crudRepository.run(session -> session.update(owner));
  }

  @Override
  public void delete(int ownerId) {
    crudRepository.run(
        "DELETE FROM Owner WHERE id = :fId",
        Map.of("fId", ownerId)
    );
  }

  @Override
  public Optional<Owner> findById(int ownerId) {
    return crudRepository.optional(
        "FROM Owner WHERE id = :fId", Owner.class,
        Map.of("fId", ownerId)
    );
  }

  @Override
  public Optional<Owner> findByName(String name) {
    return crudRepository.optional(
        "FROM Owner WHERE name = :fName", Owner.class,
        Map.of("fName", name)
    );
  }
}
