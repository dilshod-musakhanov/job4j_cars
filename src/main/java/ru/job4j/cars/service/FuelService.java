package ru.job4j.cars.service;

import ru.job4j.cars.model.Fuel;

import java.util.List;
import java.util.Optional;

public interface FuelService {
    Optional<Fuel> save(Fuel fuel);
    boolean update(Fuel fuel);
    boolean delete(int id);
    Optional<Fuel> findById(int id);
    List<Fuel> findAll();

}
