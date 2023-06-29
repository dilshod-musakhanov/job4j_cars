package ru.job4j.cars.service;

import ru.job4j.cars.model.Transmission;

import java.util.List;
import java.util.Optional;

public interface TransmissionService {
    Optional<Transmission> save(Transmission transmission);
    boolean update(Transmission transmission);
    boolean delete(int id);
    Optional<Transmission> findById(int id);
    List<Transmission> findAll();
}
