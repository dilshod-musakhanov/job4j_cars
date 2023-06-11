package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.repository.PhotoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public Optional<Photo> save(Photo photo) {
        return photoRepository.save(photo);
    }

    public boolean update(Photo photo) {
        return photoRepository.update(photo);
    }

    public boolean delete(int id) {
        return photoRepository.delete(id);
    }

    public Optional<Photo> findById(int id) {
        return photoRepository.findById(id);
    }

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }
}
