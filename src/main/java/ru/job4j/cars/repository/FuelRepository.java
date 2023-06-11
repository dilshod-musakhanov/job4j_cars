package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Fuel;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j
public class FuelRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Fuel in database
     * @param fuel Fuel
     * @return Optioonal Fuel or Empty Optional
     */
    public Optional<Fuel> save(Fuel fuel) {
        try {
            crudRepository.run(session -> session.persist(fuel));
            return Optional.of(fuel);
        } catch (Exception e) {
            log.error("Exception in saving Fuel: " + fuel + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Fuel
     * @param fuel Fuel
     * @return boolean result
     */
    public boolean update(Fuel fuel) {
        try {
            crudRepository.run(session -> session.merge(fuel));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Fuel: " + fuel + " " + e);
        }
        return false;
    }

    /**
     * Delete Fuel by ID
     * @param id ID
     * @return boolean result
     */
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE Fuel WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Fuel by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Fuel by ID
     * @param id ID
     * @return Optional Fuel or Empty Optional
     */
    public Optional<Fuel> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Fuel WHERE id = :fId",
                    Fuel.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Fuel by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Fuel
     * @return List of Fuel or Empty List
     */
    public List<Fuel> findAll() {
        try {
            var allFuel = crudRepository.query(
                    "FROM Fuel",
                    Fuel.class
            );
            return allFuel;
        } catch (Exception e) {
            log.error("Exception in finding all Fuel: " + e);
        }
        return Collections.emptyList();
    }

}
