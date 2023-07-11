package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Fuel;
import ru.job4j.cars.repository.HibernateFuelRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleFuelService implements FuelService {
    private final HibernateFuelRepository fuelRepository;

    @Override
    public Optional<Fuel> save(Fuel fuel) {
        return fuelRepository.save(fuel);
    }

    @Override
    public boolean update(Fuel fuel) {
        return fuelRepository.update(fuel);
    }

    @Override
    public boolean delete(int id) {
        return fuelRepository.delete(id);
    }

    @Override
    public Optional<Fuel> findById(int id) {
        return fuelRepository.findById(id);
    }

    @Override
    public List<Fuel> findAll() {
        return fuelRepository.findAll();
    }

}
