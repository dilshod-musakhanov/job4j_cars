package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.HibOwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleOwnerService implements OwnerService {
    private final HibOwnerRepository ownerRepository;

    @Override
    public Optional<Owner> save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public boolean update(Owner owner) {
        return ownerRepository.update(owner);
    }

    @Override
    public boolean delete(int id) {
        return ownerRepository.delete(id);
    }

    @Override
    public Optional<Owner> findById(int id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Optional<Owner> findByUser(User user) {
        return ownerRepository.findByUser(user);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
