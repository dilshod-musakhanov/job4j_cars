package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.*;

@Repository
@AllArgsConstructor
@Log4j
public class UserRepository {
    private final CrudRepository crudRepository;

    /**
     * Save User in database.
     * @param user User.
     * @return Optional User with id or Empty Optional.
     */
    public Optional<User> save(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            log.error("Exception in saving User: " + e);
        }
        return Optional.empty();
    }

    /**
     * Update User in database.
     * @param user User.
     * @return boolean result
     */
    public boolean update(User user) {
        try {
            crudRepository.run(session -> session.merge(user));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating User " + e);
        }
        return false;
    }

    /**
     * Delete User by ID.
     * @param userId ID
     * @return boolean result
     */
    public boolean delete(int userId) {
        try {
            crudRepository.run(
                    "DELETE FROM User WHERE id = :fId",
                    Map.of("fId", userId)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting User by id" + e);
        }
        return false;
    }

    /**
     * List of Users sorted by ID.
     * @return List of Users.
     */
    public List<User> findAllOrderById() {
        try {
            return crudRepository.query("FROM User ORDER BY id ASC", User.class);
        } catch (Exception e) {
            log.error("Exception in finding all order by id " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Find User by ID
     * @param userId ID
     * @return Optional User or Empty Optional.
     */
    public Optional<User> findById(int userId) {
        try {
            return crudRepository.optional(
                    "FROM User WHERE id = :fId",
                    User.class,
                    Map.of("fId", userId)
            );
        } catch (Exception e) {
            log.error("Exception in finding User by id: " + userId + " " + e);
        }
        return Optional.empty();
    }

    /**
     * List of Users by name LIKE %key%
     * @param key key
     * @return List of Users or Empty List.
     */
    public List<User> findByLikeName(String key) {
        String likePattern = String.format("%%%s%%", key);
       try {
           return crudRepository.query(
                   "FROM User WHERE name like :fKey",
                   User.class,
                   Map.of("fKey", likePattern)
           );
       } catch (Exception e) {
           log.error("Exception in finding by like name " + e);
       }
       return Collections.emptyList();
    }

    /**
     * Find User by email.
     * @param email email.
     * @return Optional User or Empty Optional.
     */
    public Optional<User> findByEmail(String email) {
        try {
            return crudRepository.optional(
                    "FROM User WHERE email = :fEmail",
                    User.class,
                    Map.of("fEmail", email)
            );
        } catch (Exception e) {
            log.error("Exception in finding by email " + e);
        }
        return Optional.empty();
    }
}
