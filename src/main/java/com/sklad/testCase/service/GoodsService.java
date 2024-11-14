package com.sklad.testCase.service;

import com.sklad.testCase.entity.Goods;
import com.sklad.testCase.exception.GoodsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService implements GoodsServiceInterface{

    private final List<Goods> goodsList = new ArrayList<>();

    private Long nextId = 1L;

    @Override
    public List<Goods> getAllGoods() {
        return goodsList;
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsList.stream()
                .filter(goods -> goods.getId() == id)
                .findFirst()
                .orElseThrow(() -> new GoodsNotFoundException("Goods not found"));
    }

    @Override
    public Goods createGoods(Goods goods) {
        goods.setId(nextId++);
        goodsList.add(goods);
        return goods;
    }

    @Override
    public Goods updateGoods(Long id, Goods updatedGoods) {
        Goods existingGoods = getGoodsById(id);
        existingGoods.setName(updatedGoods.getName());
        existingGoods.setDescription(updatedGoods.getDescription());
        existingGoods.setPrice(updatedGoods.getPrice());
        existingGoods.setInStock(updatedGoods.isInStock());
        return existingGoods;
    }

    @Override
    public void deleteGoods(Long id) {
        Goods goods = getGoodsById(id);
        goodsList.remove(goods);
    }
}
