package ru.job4j.cars.repository;

import ru.job4j.cars.model.Body;

import java.util.List;
import java.util.Optional;

public interface BodyRepository {
    Optional<Body> save(Body body);
    boolean update(Body body);
    boolean delete(int id);
    Optional<Body> findById(int id);
    List<Body> findAll();

}
