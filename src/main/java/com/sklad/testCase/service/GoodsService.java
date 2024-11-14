package com.sklad.testCase.service;

import com.sklad.testCase.entity.Goods;
import com.sklad.testCase.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
