package ru.job4j.cars.repository;

import ru.job4j.cars.model.Transmission;

import java.util.List;
import java.util.Optional;

public interface TransmissionRepository {
    Optional<Transmission> save(Transmission transmission);
    boolean update(Transmission transmission);
    boolean delete(int id);
    Optional<Transmission> findById(int id);
    List<Transmission> findAll();
}
