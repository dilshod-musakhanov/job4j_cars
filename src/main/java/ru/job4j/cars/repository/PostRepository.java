package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Repository
@AllArgsConstructor
@Log4j
public class PostRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Post in database
     * @param post Post
     * @return Optional Post or Empty optional
     */
    public Optional<Post> save(Post post) {
        try {
            crudRepository.run(session -> session.save(post));
            return Optional.of(post);
        } catch (Exception e) {
            log.error("Exception in saving Post: " + post + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Post in database
     * @param post Post
     * @return boolean result
     */
    public boolean update(Post post) {
        try {
            crudRepository.run(session -> session.merge(post));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Post: " + post + " " + e);
        }
        return false;
    }

    /**
     * Delete Post by ID
     * @param id ID
     * @return boolean result
     */
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE File WHERE auto_post_id = :fId",
                    Map.of("fId", id)
            );

            crudRepository.run(
                    "DELETE Post WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Post: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Post by ID
     * @param id ID
     * @return Optional Post or Empty Optional
     */
    public Optional<Post> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Post AS p JOIN FETCH p.files WHERE p.id = :fId",
                    Post.class,
                    Map.of("fId", id)
            );
        } catch (Exception e) {
            log.error("Exception in finding Post by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Posts
     * @return List of Post
     */
    public List<Post> findAll() {
        try {
            var allPosts = crudRepository.query(
                    "SELECT DISTINCT p FROM Post AS p JOIN FETCH p.car JOIN FETCH p.files",
                    Post.class
            );
            return allPosts;
        } catch (Exception e) {
            log.error("Exception in finding all Post: " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Find Posts created on the previous day
     * @return List of Posts or Empty List
     */
    public List<Post> findByCreatedInThreeLastDays() {
        try {
            LocalDateTime startOfDay = LocalDateTime.now().minusDays(2).with(LocalTime.MIN);
            LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
            return crudRepository.query(
                    "SELECT DISTINCT p FROM Post AS p JOIN FETCH p.files WHERE p.created >= :startOfDay AND p.created <= :endOfDay",
                    Post.class,
                    Map.of("startOfDay", startOfDay, "endOfDay", endOfDay)
            );
        } catch (Exception e) {
            log.error("Exception in finding Posts created the previous day " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Find Posts as per Brand name
     * @param brandName name
     * @return List of Posts or Empty List
     */
    public List<Post> findByBrandName(String brandName) {
        try {
            return crudRepository.query(
                    "SELECT DISTINCT p FROM Post AS p JOIN FETCH p.files AS f JOIN FETCH p.car AS c JOIN c.brand AS b WHERE b.name = :fName",
                    Post.class,
                    Map.of("fName", brandName)
            );
        } catch (Exception e) {
            log.error("Exception in finding Posts by Brand name " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Find Post with Photo
     * @return List of Posts or Empty List
     */
    public List<Post> findWithPhotos() {
        try {
            return crudRepository.query(
                    "SELECT DISTINCT p FROM Post AS p JOIN FETCH p.files f WHERE LENGTH(f.name) > 1",
                    Post.class
            );
        } catch (Exception e) {
            log.error("Exception in finding Posts with files " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Update status of Post by ID
     * @param id Post ID
     * @return boolean result
     */
    public boolean updateState(int id) {
        try {
            crudRepository.run(
                    "UPDATE Post SET carSold = :fCarSold where id = :fId",
                    Map.of("fCarSold", true, "fId", id)
            );
            return true;
        } catch (Exception e) {
        log.error("Exception in updating Car status " + e);
    }
        return false;
    }

    /**
     * Find Post by Condition carNew
     * @param carNew Condition
     * @return List of Posts or Empty List
     */
    public List<Post> byCondition(boolean carNew) {
        try {
            return crudRepository.query(
                    "SELECT DISTINCT p FROM Post AS p JOIN FETCH p.files WHERE p.carNew = :fCarNew",
                    Post.class,
                    Map.of("fCarNew", carNew)
            );
        } catch (Exception e) {
            log.error("Exception in finding Post by Car condition: " + carNew + " " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Find Posts by Fuel type
     * @param fuelType Fuel type
     * @return List of Posts or Empty List
     */
    public List<Post> byFuelType(String fuelType) {
        try {
            return crudRepository.query(
                    "SELECT DISTINCT p FROM Post AS p JOIN FETCH p.files JOIN FETCH p.car car JOIN car.fuel f WHERE f.name = :fuelType",
                    Post.class,
                    Map.of("fuelType", fuelType)
            );
        } catch (Exception e) {
            log.error("Exception in finding Post by fuel type: " + fuelType + " " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Find Posts by Status carSold
     * @param carSold Status
     * @return List of Posts or Empty List
     */
    public List<Post> byStatus(boolean carSold) {
        try {
            return crudRepository.query(
                    "SELECT DISTINCT p FROM Post AS p JOIN FETCH p.files WHERE p.carSold = :fCarSold",
                    Post.class,
                    Map.of("fCarSold", carSold)
            );
        } catch (Exception e) {
            log.error("Exception in finding Post by Car status: " + carSold + " " + e);
        }
        return Collections.emptyList();
    }

}

