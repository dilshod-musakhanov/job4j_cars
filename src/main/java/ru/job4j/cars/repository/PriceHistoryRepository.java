package ru.job4j.cars.repository;

import ru.job4j.cars.model.PriceHistory;

import java.util.Optional;

public interface PriceHistoryRepository {
    Optional<PriceHistory> save(PriceHistory priceHistory);
    boolean update(PriceHistory priceHistory);
    boolean delete(int id);
    Optional<PriceHistory> findById(int id);

}
