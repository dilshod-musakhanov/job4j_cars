package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.PriceHistoryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceHistoryService {
    private final PriceHistoryRepository priceHistoryRepository;

    public Optional<PriceHistory> save(PriceHistory priceHistory) {
        return priceHistoryRepository.save(priceHistory);
    }

    public boolean update(PriceHistory priceHistory) {
        return priceHistoryRepository.update(priceHistory);
    }

    public boolean delete(int id) {
        return priceHistoryRepository.delete(id);
    }

    public Optional<PriceHistory> findById(int id) {
        return priceHistoryRepository.findById(id);
    }

}
