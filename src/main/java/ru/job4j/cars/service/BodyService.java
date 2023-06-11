package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Body;
import ru.job4j.cars.repository.BodyRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BodyService {
    private BodyRepository bodyRepository;

    public Optional<Body> save(Body body) {
        return bodyRepository.save(body);
    }

    public boolean update(Body body) {
        return bodyRepository.update(body);
    }

    public boolean delete(int id) {
        return bodyRepository.delete(id);
    }

    public Optional<Body> findById(int id) {
        return bodyRepository.findById(id);
    }

    public List<Body> findAll() {
        return bodyRepository.findAll();
    }
}
