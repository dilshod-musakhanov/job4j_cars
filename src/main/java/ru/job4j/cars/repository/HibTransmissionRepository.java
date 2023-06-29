package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Transmission;

import java.util.*;

@Repository
@AllArgsConstructor
@Log4j
public class HibTransmissionRepository implements TransmissionRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Transmission in database
     * @param transmission Transmission
     * @return Optional Transmission or Empty Optional
     */
    @Override
    public Optional<Transmission> save(Transmission transmission) {
        try {
            crudRepository.run(session -> session.persist(transmission));
            return Optional.of(transmission);
        } catch (Exception e) {
            log.error("Exception in saving Transmission: " + transmission + " " + e);
        }
        return Optional.empty();
    }


    /**
     * Update Transmission
     * @param transmission Transmission
     * @return boolean result
     */
    @Override
    public boolean update(Transmission transmission) {
        try {
            crudRepository.run(session -> session.merge(transmission));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Transmission: " + transmission + " " + e);
        }
        return false;
    }

    /**
     * Delete Transmission by ID
     * @param id ID
     * @return boolean result
     */
    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE Transmission WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Transmission by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Transmission by ID
     * @param id ID
     * @return Optional Transmission or Empty Optional
     */
    @Override
    public Optional<Transmission> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Transmission WHERE id = :fId",
                    Transmission.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Transmission by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Transmissions
     * @return List of Transmissions or Empty List
     */
    @Override
    public List<Transmission> findAll() {
        try {
            var allTransmissions = crudRepository.query(
                    "FROM Transmission",
                    Transmission.class
            );
            return allTransmissions;
        } catch (Exception e) {
            log.error("Exception in finding all Transmission: " + e);
        }
        return Collections.emptyList();
    }
}
