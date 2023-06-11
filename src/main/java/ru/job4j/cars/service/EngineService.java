package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;

    public Optional<Engine> save(Engine engine) {
        return engineRepository.save(engine);
    }

    public boolean update(Engine engine) {
        return engineRepository.update(engine);
    }

    public boolean delete(int id) {
        return engineRepository.delete(id);
    }

    public Optional<Engine> findById(int id) {
        return engineRepository.findById(id);
    }

    public List<Engine> findAll() {
        return engineRepository.findAll();
    }
}
