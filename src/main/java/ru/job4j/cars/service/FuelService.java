package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Fuel;
import ru.job4j.cars.repository.FuelRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FuelService {
    private final FuelRepository fuelRepository;

    public Optional<Fuel> save(Fuel fuel) {
        return fuelRepository.save(fuel);
    }

    public boolean update(Fuel fuel) {
        return fuelRepository.update(fuel);
    }

    public boolean delete(int id) {
        return fuelRepository.delete(id);
    }

    public Optional<Fuel> findById(int id) {
        return fuelRepository.findById(id);
    }

    public List<Fuel> findAll() {
        return fuelRepository.findAll();
    }

}
