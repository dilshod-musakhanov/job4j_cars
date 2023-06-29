package ru.job4j.cars.service;

import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.model.File;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {
    File save(FileDto fileDto);
    List<File> convertMultipartFileToFile(List<MultipartFile> files) throws IOException;
    Optional<File> save(File file);
    Optional<FileDto> getFileById(int id);
    boolean update(File file);
    boolean delete(int id);
    List<File> findAll();
}
