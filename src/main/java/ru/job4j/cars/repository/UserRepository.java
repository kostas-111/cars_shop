package ru.job4j.cars.repository;

import java.util.List;
import java.util.Optional;
import ru.job4j.cars.model.User;

public interface UserRepository {

  User create(User user);

  void update(User user);

  void delete(int userId);

  List<User> findAllOrderById();

  Optional<User> findById(int userId);

  List<User> findByLikeLogin(String key);

  Optional<User> findByLogin(String login);

}
