package com.sklad.testCase.service;

import com.sklad.testCase.entity.Goods;
import com.sklad.testCase.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsService implements GoodsServiceInterface{

    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    public Optional<Goods> getGoodsById(Long id) {
        return goodsRepository.findById(id);
    }

    public Goods createGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    public Goods updateGoods(Long id, Goods updatedGoods) {
        Goods existingGoods = goodsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goods not found"));
        existingGoods.setName(updatedGoods.getName());
        existingGoods.setDescription(updatedGoods.getDescription());
        existingGoods.setPrice(updatedGoods.getPrice());
        existingGoods.setInStock(updatedGoods.getInStock());
        return goodsRepository.save(existingGoods);
    }

    public void deleteGoods(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public List<Goods> filterGoods(String name, Double minPrice, Double maxPrice, Boolean inStock, String sortBy, String order, Integer limit) {
        List<Goods> filteredGoods = goodsRepository.findAll();

        // Фильтрация
        if (name != null && !name.isBlank()) {
            filteredGoods = filteredGoods.stream()
                    .filter(goods -> goods.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if (minPrice != null) {
            filteredGoods = filteredGoods.stream()
                    .filter(goods -> goods.getPrice() != null && goods.getPrice() >= minPrice)
                    .toList();
        }

        // Фильтрация по максимальной цене
        if (maxPrice != null) {
            filteredGoods = filteredGoods.stream()
                    .filter(goods -> goods.getPrice() != null && goods.getPrice() <= maxPrice)
                    .toList();
        }

        if (inStock != null) {
            filteredGoods = filteredGoods.stream()
                    .filter(goods -> goods.isInStock().equals(inStock))
                    .toList();
        }

        // Сортировка
        if (sortBy != null) {
            Comparator<Goods> comparator;
            if ("price".equalsIgnoreCase(sortBy)) {
                comparator = Comparator.comparing(Goods::getPrice);
            } else if ("name".equalsIgnoreCase(sortBy)) {
                comparator = Comparator.comparing(Goods::getName, String.CASE_INSENSITIVE_ORDER);
            } else {
                throw new IllegalArgumentException("Invalid sortBy parameter: " + sortBy);
            }

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }

            filteredGoods = filteredGoods.stream()
                    .sorted(comparator)
                    .toList();
        }

        // Лимит
        if (limit != null && limit > 0) {
            filteredGoods = filteredGoods.stream()
                    .limit(limit)
                    .toList();
        }

        return filteredGoods;
    }
}
