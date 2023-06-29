package ru.job4j.cars.service;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> save(Post post);
    boolean update(Post post);
    boolean delete(int id);
    Optional<Post> findById(int id);
    List<Post> findAll();
    List<Post> findByCreatedInThreeLastDays();
    List<Post> findByBrandName(String brandName);
    List<Post> findWithPhotos();
    boolean updateState(int id);
    List<Post> byCondition(boolean carNew);
    List<Post> byFuelType(String fuelType);
    List<Post> byStatus(boolean carSold);
}
