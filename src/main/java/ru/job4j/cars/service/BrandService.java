package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Optional<Brand> save(Brand brand) {
        return brandRepository.save(brand);
    }

    public boolean update(Brand brand) {
        return brandRepository.update(brand);
    }

    public boolean delete(int id) {
        return brandRepository.delete(id);
    }

    public Optional<Brand> findById(int id) {
        return brandRepository.findById(id);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
