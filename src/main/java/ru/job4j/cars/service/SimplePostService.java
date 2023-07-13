package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.mapper.PostMapper;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.HibernatePostRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {
    private final HibernatePostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public Optional<Post> save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public boolean update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public boolean delete(int id) {
        return postRepository.delete(id);
    }

    @Override
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByCreatedInThreeLastDays() {
        return postRepository.findByCreatedInThreeLastDays();
    }

    @Override
    public List<Post> findByBrandName(String brandName) {
        String formattedBrandName = brandName.substring(0, 1).toUpperCase() + brandName.substring(1).toLowerCase();
        return postRepository.findByBrandName(formattedBrandName);
    }

    @Override
    public List<Post> findWithPhotos() {
        return postRepository.findWithPhotos();
    }

    @Override
    public boolean updateState(int id) {
        return postRepository.updateState(id);
    }

    @Override
    public List<Post> byCondition(boolean carNew) {
        return postRepository.byCondition(carNew);
    }

    @Override
    public List<Post> byFuelType(String fuelType) {
        return postRepository.byFuelType(fuelType);
    }

    @Override
    public List<Post> byStatus(boolean carSold) {
        return postRepository.byStatus(carSold);
    }

    @Override
    public void toPost(PostDto postDto, User user, List<MultipartFile> files) throws IOException {
        var post = postMapper.toPost(postDto, user, files);
        postRepository.save(post);
    }
}
