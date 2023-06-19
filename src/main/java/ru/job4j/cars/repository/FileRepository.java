package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;

import java.util.*;

@Repository
@AllArgsConstructor
@Log4j
public class FileRepository {
    private final CrudRepository crudRepository;

    /**
     * Save File in database
     * @param file File
     * @return Optional File or Empty Optional
     */
    public Optional<File> save(File file) {
        try {
            crudRepository.run(session -> session.persist(file));
            return Optional.of(file);
        } catch (Exception e) {
            log.error("Exception in saving File: " + file + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update File
     * @param file File
     * @return boolean result
     */
    public boolean update(File file) {
        try {
            crudRepository.run(session -> session.merge(file));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating File: " + file + " " + e);
        }
        return false;
    }

    /**
     * Delete File by ID
     * @param id ID
     * @return boolean result
     */
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE File WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting File by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find File by ID
     * @param id ID
     * @return Optional File or Empty Optional
     */
    public Optional<File> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM File AS e WHERE e.id = :fId",
                    File.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding File by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Files
     * @return List of Files or Empty List
     */
    public List<File> findAll() {
        try {
            var allFiles = crudRepository.query(
                    "FROM File",
                    File.class
            );
            return allFiles;
        } catch (Exception e) {
            log.error("Exception in finding all File: " + e);
        }
        return Collections.emptyList();
    }
}
