package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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
                    "FROM Post AS p WHERE p.id = :fId",
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
    public Collection<Post> findAll() {
        try {
            var allPosts = crudRepository.query(
                    "FROM Post",
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
    public Collection<Post> findByCreatedPreviousDay() {
        try {
            LocalDateTime previousDay = LocalDateTime.now().minusDays(1);
            return crudRepository.query(
                    "FROM Post WHERE created = :fCreated",
                    Post.class,
                    Map.of("fCreated", previousDay)
            );
        } catch (Exception e) {
            log.error("Exception in finding Posts created the previous day " + e);
        }
        return Collections.emptyList();
    }

    /**
     * Find Posts as per Car name
     * @param carName name
     * @return List of Posts or Empty List
     */
    public Collection<Post> findByCarName(String carName) {
        try {
            return crudRepository.query(
                    "FROM Post AS p JOIN FETCH p.car AS c WHERE c.name = :fName",
                    Post.class,
                    Map.of("fName", carName)
            );
        } catch (Exception e) {
            log.error("Exception in finding Posts by Car name " + e);
        }
        return Collections.emptyList();
    }

    public Collection<Post> findWithPhotos() {
        try {
            return crudRepository.query(
                    "FROM Post AS p JOIN FETCH p.photos WHERE size(photos) > 0",
                    Post.class
            );
        } catch (Exception e) {
            log.error("Exception in finding Posts by Car name " + e);
        }
        return Collections.emptyList();
    }

}

