package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Photo;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j
public class PhotoRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Photo in database
     * @param photo Photo
     * @return Optional Photo or Empty Optional
     */
    public Optional<Photo> save(Photo photo) {
        try {
            crudRepository.run(session -> session.persist(photo));
            return Optional.of(photo);
        } catch (Exception e) {
            log.error("Exception in saving Photo: " + photo + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Photo
     * @param photo Photo
     * @return boolean result
     */
    public boolean update(Photo photo) {
        try {
            crudRepository.run(session -> session.merge(photo));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Photo: " + photo + " " + e);
        }
        return false;
    }

    /**
     * Delete Photo by ID
     * @param id ID
     * @return boolean result
     */
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE Photo WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Photo by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Photo by ID
     * @param id ID
     * @return Optional Photo or Empty Optional
     */
    public Optional<Photo> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Photo AS e WHERE e.id = :fId",
                    Photo.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Photo by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Photos
     * @return List of Photos or Empty List
     */
    public Collection<Photo> findAll() {
        try {
            var allPhotos = crudRepository.query(
                    "FROM Photo",
                    Photo.class
            );
            return allPhotos;
        } catch (Exception e) {
            log.error("Exception in finding all Photo: " + e);
        }
        return Collections.emptyList();
    }
}
