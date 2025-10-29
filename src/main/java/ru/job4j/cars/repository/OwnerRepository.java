package ru.job4j.cars.repository;

import java.util.Optional;
import ru.job4j.cars.model.Owner;

public interface OwnerRepository {

  Owner create(Owner owner);

  void update(Owner owner);

  void delete(int ownerId);

  Optional<Owner> findById(int ownerId);

  Optional<Owner> findByName(String name);
}
