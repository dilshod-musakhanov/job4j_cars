package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j
public class EngineRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Engine in database
     * @param engine Engine
     * @return Optional Engine or Empty Optional
     */
    public Optional<Engine> save(Engine engine) {
        try {
            crudRepository.run(session -> session.persist(engine));
            return Optional.of(engine);
        } catch (Exception e) {
            log.error("Exception in saving Engine: " + engine + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Engine
     * @param engine Engine
     * @return boolean result
     */
   public boolean update(Engine engine) {
        try {
            crudRepository.run(session -> session.merge(engine));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Engine: " + engine + " " + e);
        }
       return false;
   }

    /**
     * Delete Engine by ID
     * @param id ID
     * @return boolean result
     */
   public boolean delete(int id) {
       try {
           crudRepository.run(
                   "DELETE Engine WHERE id = :fId",
                   Map.of("fId", id)
           );
           return true;
       } catch (Exception e) {
           log.error("Exception in deleting Engine by id: " + id + " " + e);
       }
       return false;
   }

    /**
     * Find Engine by ID
     * @param id ID
     * @return Optional Engine or Empty Optional
     */
    public Optional<Engine> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Engine AS e WHERE e.id = :fId",
                    Engine.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Engine by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Engines
     * @return List of Engines or Empty List
     */
    public Collection<Engine> findAll() {
        try {
            var allEngines = crudRepository.query(
                    "FROM Engine",
                    Engine.class
            );
            return allEngines;
        } catch (Exception e) {
            log.error("Exception in finding all Engine: " + e);
        }
        return Collections.emptyList();
    }
}
