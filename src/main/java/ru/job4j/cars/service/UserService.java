package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    public boolean update(User user) {
        return userRepository.update(user);
    }

    public boolean delete(int userId) {
        return userRepository.delete(userId);
    }

    public List<User> findAllOrderById() {
        return userRepository.findAllOrderById();
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public List<User> findByLikeName(String key) {
        return userRepository.findByLikeName(key);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
