package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j
public class PriceHistoryRepository {
    private final CrudRepository crudRepository;

    /**
     * Save PriceHistory in database
     * @param priceHistory PriceHistory
     * @return Optional PriceHistory or Empty Optional
     */
    public Optional<PriceHistory> save(PriceHistory priceHistory) {
        try {
            crudRepository.run(session -> session.persist(priceHistory));
            return Optional.of(priceHistory);
        } catch (Exception e) {
            log.error("Exception in saving PriceHistory " + e);
        }
        return Optional.empty();
    }

    /**
     * Update PriceHistory in database
     * @param priceHistory PriceHistory
     * @return boolean result
     */
    public boolean update(PriceHistory priceHistory) {
        try {
            crudRepository.run(session -> session.merge(priceHistory));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating PriceHistory " + e);
        }
        return false;
    }

    /**
     * Delete PriceHistory by ID
     * @param id ID
     * @return boolean result
     */
    public boolean delete(int id) {
        try {
            crudRepository.query(
                    "DELETE FROM PriceHistory WHERE id = :fId",
                    PriceHistory.class,
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting PriceHistory " + e);
        }
        return false;
    }

    /**
     * Find PriceHistory by ID
     * @param id ID
     * @return Optional PriceHistory or Empty Optional
     */
    public Optional<PriceHistory> findById(int id) {
        try {
            crudRepository.optional(
                    "FROM PriceHistory WHERE id = :fId",
                    PriceHistory.class,
                    Map.of("fId", id)
            );
        } catch (Exception e) {
            log.error("Exception in deleting PriceHistory " + e);
        }
        return Optional.empty();
    }

}
