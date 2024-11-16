package com.sklad.testCase.service;

import com.sklad.testCase.entity.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodsServiceInterface {

    List<Goods> getAllGoods();

    Optional<Goods> getGoodsById(Long id);

    Goods createGoods(Goods product);

    Goods updateGoods(Long id, Goods updatedProduct);

    void deleteGoods(Long id);

    List<Goods> filterGoods(String name, Double minPrice, Double maxPrice,
                            Boolean inStock, String sortBy, String order, Integer limit);

}
