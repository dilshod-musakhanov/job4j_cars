package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j
public class OwnerRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Owner
     * @param owner Owner
     * @return Optional Owner or Empty Optional
     */
    public Optional<Owner> save(Owner owner) {
        try {
            crudRepository.run(session -> session.persist(owner));
            return Optional.of(owner);
        } catch (Exception e) {
            log.error("Exception in saving Owner: " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Owner
     * @param owner Owner
     * @return boolean result
     */
    public boolean update(Owner owner) {
        try {
            crudRepository.run(session -> session.merge(owner));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Owner: " + e);
        }
        return false;
    }

    /**
     * Delete Owner by ID
     * @param id ID
     * @return boolean result
     */
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE Owner WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Owner by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Owner by ID
     * @param id ID
     * @return Optional Owner or Empty Optional
     */
    public Optional<Owner> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Owner AS e WHERE e.id = :fId",
                    Owner.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Owner by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Owners
     * @return List of Owners or Empty List
     */
    public Collection<Owner> findAll() {
        try {
            var allOwners = crudRepository.query(
                    "FROM Owner",
                    Owner.class
            );
            return allOwners;
        } catch (Exception e) {
            log.error("Exception in finding all Owner: " + e);
        }
        return Collections.emptyList();
    }

}
