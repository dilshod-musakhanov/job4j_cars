package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j
public class HibBrandRepository implements BrandRepository {
    private final CrudRepository crudRepository;

    /**
     * Save Brand in database
     * @param brand Brand
     * @return Optioonal Brand or Empty Optional
     */
    @Override
    public Optional<Brand> save(Brand brand) {
        try {
            crudRepository.run(session -> session.persist(brand));
            return Optional.of(brand);
        } catch (Exception e) {
            log.error("Exception in saving Brand: " + brand + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Update Brand
     * @param brand Brand
     * @return boolean result
     */
    @Override
    public boolean update(Brand brand) {
        try {
            crudRepository.run(session -> session.merge(brand));
            return true;
        } catch (Exception e) {
            log.error("Exception in updating Brand: " + brand + " " + e);
        }
        return false;
    }

    /**
     * Delete Brand by ID
     * @param id ID
     * @return boolean result
     */
    @Override
    public boolean delete(int id) {
        try {
            crudRepository.run(
                    "DELETE Brand WHERE id = :fId",
                    Map.of("fId", id)
            );
            return true;
        } catch (Exception e) {
            log.error("Exception in deleting Brand by id: " + id + " " + e);
        }
        return false;
    }

    /**
     * Find Brand by ID
     * @param id ID
     * @return Optional Brand or Empty Optional
     */
    @Override
    public Optional<Brand> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Brand WHERE id = :fId",
                    Brand.class,
                    Map.of("fId", id)

            );
        } catch (Exception e) {
            log.error("Exception in finding Brand by id: " + id + " " + e);
        }
        return Optional.empty();
    }

    /**
     * Find all Brands
     * @return List of Brands or Empty List
     */
    @Override
    public List<Brand> findAll() {
        try {
            var allBrands = crudRepository.query(
                    "FROM Brand",
                    Brand.class
            );
            return allBrands;
        } catch (Exception e) {
            log.error("Exception in finding all Brand: " + e);
        }
        return Collections.emptyList();
    }
}
