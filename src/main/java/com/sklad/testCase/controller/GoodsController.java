package com.sklad.testCase.controller;

import com.sklad.testCase.entity.Goods;
import com.sklad.testCase.service.GoodsServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController implements GoodsControllerInterface{

    private final GoodsServiceInterface goodsService;

    public GoodsController(GoodsServiceInterface goodsService) {
        this.goodsService = goodsService;
    }

    @Override
    @GetMapping
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

    @Override
    @GetMapping("/{id}")
    public Goods getGoodsById(@PathVariable Long id) {
        return goodsService.getGoodsById(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<Goods> createGoods(@Valid @RequestBody Goods goods) {
        Goods createdGoods = goodsService.createGoods(goods);
        return new ResponseEntity<>(createdGoods, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public Goods updateGoods(@PathVariable Long id, @Valid @RequestBody Goods goods) {
        return goodsService.updateGoods(id, goods);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoods(@PathVariable Long id) {
        goodsService.deleteGoods(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
