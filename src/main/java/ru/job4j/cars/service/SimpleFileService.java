package ru.job4j.cars.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.model.File;
import ru.job4j.cars.repository.HibernateFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class SimpleFileService implements FileService {
    private final HibernateFileRepository fileRepository;
    private final String storageDirectory;

    public SimpleFileService(HibernateFileRepository fileRepository,
                             @Value("${file.directory}") String storageDirectory) {
        this.fileRepository = fileRepository;
        this.storageDirectory = storageDirectory;
        createStorageDirectory(storageDirectory);
    }

    private void createStorageDirectory(String path) {
        var directory = Path.of(path);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(Path.of(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public File save(FileDto fileDto) {
        var path = getPath(fileDto.getName());
        writeFileBytes(path, fileDto.getContent());
        return fileRepository.save(new File(fileDto.getName(), path)).get();
    }

    @Override
    public List<File> convertMultipartFileToFile(List<MultipartFile> files) throws IOException {
        List<File> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            File convertedFile = save(new FileDto(file.getOriginalFilename(), file.getBytes()));
            fileList.add(convertedFile);
        }
        fileList.sort(Comparator.comparing(File::getName));
        return fileList;
    }

    private String getPath(String fileName) {
        return storageDirectory + java.io.File.separator + UUID.randomUUID() + fileName;
    }

    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    private byte[] readFileBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<FileDto> getFileById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        var content = readFileBytes(fileOptional.get().getPath());
        return Optional.of(new FileDto(fileOptional.get().getName(), content));
    }

    @Override
    public Optional<File> save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public boolean update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public boolean delete(int id) {
        return fileRepository.delete(id);
    }

    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }
}
