package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.HibBrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleBrandService implements BrandService {
    private final HibBrandRepository brandRepository;

    @Override
    public Optional<Brand> save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public boolean update(Brand brand) {
        return brandRepository.update(brand);
    }

    @Override
    public boolean delete(int id) {
        return brandRepository.delete(id);
    }

    @Override
    public Optional<Brand> findById(int id) {
        return brandRepository.findById(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
