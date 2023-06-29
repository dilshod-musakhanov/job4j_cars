package ru.job4j.cars.service;

import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Optional<Owner> save(Owner owner);
    boolean update(Owner owner);
    boolean delete(int id);
    Optional<Owner> findById(int id);
    Optional<Owner> findByUser(User user);
    List<Owner> findAll();
}
