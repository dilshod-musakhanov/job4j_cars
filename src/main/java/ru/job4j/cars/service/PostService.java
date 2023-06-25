package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Optional<Post> save(Post post) {
        return postRepository.save(post);
    }

    public boolean update(Post post) {
        return postRepository.update(post);
    }

    public boolean delete(int id) {
        return postRepository.delete(id);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findByCreatedInThreeLastDays() {
        return postRepository.findByCreatedInThreeLastDays();
    }

    public List<Post> findByBrandName(String brandName) {
        String formattedBrandName = brandName.substring(0, 1).toUpperCase() + brandName.substring(1).toLowerCase();
        return postRepository.findByBrandName(formattedBrandName);
    }

    public List<Post> findWithPhotos() {
        return postRepository.findWithPhotos();
    }

    public boolean updateState(int id) {
        return postRepository.updateState(id);
    }

    public List<Post> byCondition(boolean carNew) {
        return postRepository.byCondition(carNew);
    }

    public List<Post> byFuelType(String fuelType) {
        return postRepository.byFuelType(fuelType);
    }

    public List<Post> byStatus(boolean carSold) {
        return postRepository.byStatus(carSold);
    }
}
