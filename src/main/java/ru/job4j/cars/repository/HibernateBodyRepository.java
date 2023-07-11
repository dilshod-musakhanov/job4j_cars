package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Body;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j
public class HibernateBodyRepository implements BodyRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Body in database
     * @param body Body
     * @return Optioonal Body or Empty Optional
     */
    @Override
    public Optional<Body> save(Body body) {
        try {
            crudRepository.run(session -> session.persist(body));
            return Optional.of(body);
        } catch (Exception e) {
            log.error("Exception in saving Body: " + body + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Body
     * @param body Body
     * @return boolean result
     */
    @Override
    public boolean update(Body body) {
        try {
            crudRepository.run(session -> session.merge(body));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Body: " + body + " " + e);
        }
        return false;
    }

    /**
     * Delete Body by ID
     * @param id ID
     * @return boolean result
     */
    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE Body WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Body by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Body by ID
     * @param id ID
     * @return Optional Body or Empty Optional
     */
    @Override
    public Optional<Body> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Body WHERE id = :fId",
                    Body.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Body by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Bodies
     * @return List of Bodies or Empty List
     */
    @Override
    public List<Body> findAll() {
        try {
            var allBodies = crudRepository.query(
                    "FROM Body",
                    Body.class
            );
            return allBodies;
        } catch (Exception e) {
            log.error("Exception in finding all Body: " + e);
        }
        return Collections.emptyList();
    }
}
