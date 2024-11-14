package com.sklad.testCase.controller;

import com.sklad.testCase.entity.Goods;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GoodsControllerInterface {

    List<Goods> getAllGoods();

    Goods getGoodsById(Long id);

    ResponseEntity<Goods> createGoods(Goods goods);

    Goods updateGoods(Long id, Goods goods);

    ResponseEntity<Void> deleteGoods(Long id);
}
