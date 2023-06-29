package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Body;
import ru.job4j.cars.repository.HibBodyRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleBodyService implements BodyService {
    private HibBodyRepository bodyRepository;

    @Override
    public Optional<Body> save(Body body) {
        return bodyRepository.save(body);
    }

    @Override
    public boolean update(Body body) {
        return bodyRepository.update(body);
    }

    @Override
    public boolean delete(int id) {
        return bodyRepository.delete(id);
    }

    @Override
    public Optional<Body> findById(int id) {
        return bodyRepository.findById(id);
    }

    @Override
    public List<Body> findAll() {
        return bodyRepository.findAll();
    }
}
