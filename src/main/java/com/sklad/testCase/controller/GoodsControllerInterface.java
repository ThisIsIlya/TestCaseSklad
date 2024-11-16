package com.sklad.testCase.controller;

import com.sklad.testCase.entity.Goods;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GoodsControllerInterface {

    List<Goods> getAllGoods();

    Optional<Goods> getGoodsById(Long id);

    ResponseEntity<Goods> createGoods(Goods goods);

    Goods updateGoods(Long id, Goods goods);

    ResponseEntity<Void> deleteGoods(Long id);

    List<Goods> filterGoods(
            String name,
            Double minPrice,
            Double maxPrice,
            Boolean inStock,
            String sortBy,
            String order,
            Integer limit
    );
}
