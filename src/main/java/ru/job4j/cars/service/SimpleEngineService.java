package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.HibernateEngineRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleEngineService implements EngineService {
    private final HibernateEngineRepository engineRepository;

    @Override
    public Optional<Engine> save(Engine engine) {
        return engineRepository.save(engine);
    }

    @Override
    public boolean update(Engine engine) {
        return engineRepository.update(engine);
    }

    @Override
    public boolean delete(int id) {
        return engineRepository.delete(id);
    }

    @Override
    public Optional<Engine> findById(int id) {
        return engineRepository.findById(id);
    }

    @Override
    public List<Engine> findAll() {
        return engineRepository.findAll();
    }
}
