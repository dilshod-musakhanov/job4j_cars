package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.util.List;
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

    public List<Post> findByCreatedPreviousDay() {
        return postRepository.findByCreatedPreviousDay();
    }

    public List<Post> findByCarName(String carName) {
        return postRepository.findByCarName(carName);
    }

    public List<Post> findWithPhotos() {
        return postRepository.findWithPhotos();
    }

}
