package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Transmission;
import ru.job4j.cars.repository.HibTransmissionRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTransmissionService implements TransmissionService {
    private final HibTransmissionRepository transmissionRepository;

    @Override
    public Optional<Transmission> save(Transmission transmission) {
        return transmissionRepository.save(transmission);
    }

    @Override
    public boolean update(Transmission transmission) {
        return transmissionRepository.update(transmission);
    }

    @Override
    public boolean delete(int id) {
        return transmissionRepository.delete(id);
    }

    @Override
    public Optional<Transmission> findById(int id) {
        return transmissionRepository.findById(id);
    }

    @Override
    public List<Transmission> findAll() {
        return transmissionRepository.findAll();
    }
}
