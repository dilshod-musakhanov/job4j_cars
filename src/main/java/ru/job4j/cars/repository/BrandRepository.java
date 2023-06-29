package ru.job4j.cars.repository;

import ru.job4j.cars.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    Optional<Brand> save(Brand brand);
    boolean update(Brand brand);
    boolean delete(int id);
    Optional<Brand> findById(int id);
    List<Brand> findAll();
}
