package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.*;

@Repository
@AllArgsConstructor
@Log4j
public class CarRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Car in database
     * @param car Car
     * @return Optioonal Car or Empty Optional
     */
    public Optional<Car> save(Car car) {
        try {
            crudRepository.run(session -> session.persist(car));
            return Optional.of(car);
        } catch (Exception e) {
            log.error("Exception in saving Car: " + car + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Car
     * @param car Car
     * @return boolean result
     */
    public boolean update(Car car) {
        try {
            crudRepository.run(session -> session.merge(car));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Car: " + car + " " + e);
        }
        return false;
    }

    /**
     * Delete Car by ID
     * @param id ID
     * @return boolean result
     */
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE Car WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Car by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Car by ID
     * @param id ID
     * @return Optional Car or Empty Optional
     */
    public Optional<Car> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Car WHERE id = :fId",
                    Car.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Car by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Cars
     * @return List of Cars or Empty List
     */
    public List<Car> findAll() {
        try {
            var allCars = crudRepository.query(
                    "FROM Car",
                    Car.class
            );
            return allCars;
        } catch (Exception e) {
            log.error("Exception in finding all Car: " + e);
        }
        return Collections.emptyList();
    }
}
