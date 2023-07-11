package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.HibernatePriceHistoryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePriceHistoryService implements PriceHistoryService {
    private final HibernatePriceHistoryRepository priceHistoryRepository;

    @Override
    public Optional<PriceHistory> save(PriceHistory priceHistory) {
        return priceHistoryRepository.save(priceHistory);
    }

    @Override
    public boolean update(PriceHistory priceHistory) {
        return priceHistoryRepository.update(priceHistory);
    }

    @Override
    public boolean delete(int id) {
        return priceHistoryRepository.delete(id);
    }

    @Override
    public Optional<PriceHistory> findById(int id) {
        return priceHistoryRepository.findById(id);
    }

}
