package com.sklad.testCase.service;

import com.sklad.testCase.entity.Goods;
import com.sklad.testCase.exception.GoodsNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoodsServiceTest {

        private GoodsService goodsService;

        @BeforeEach
        void setUp() {
            goodsService = new GoodsService();
        }

        @Test
        void testCreateGoods() {
            Goods goods = new Goods(null, "Test Goods", "Description", 100.0, true);
            Goods createdGoods = goodsService.createGoods(goods);

            assertNotNull(createdGoods.getId());
            assertEquals("Test Goods", createdGoods.getName());
        }

        @Test
        void testGetAllGoods() {
            goodsService.createGoods(new Goods(null, "Goods 1", "Description 1", 50.0, true));
            goodsService.createGoods(new Goods(null, "Goods 2", "Description 2", 150.0, false));

            List<Goods> goodsList = goodsService.getAllGoods();

            assertEquals(2, goodsList.size());
        }

        @Test
        void testGetGoodsById() {
            Goods goods = goodsService.createGoods(new Goods(null, "Goods", "Description", 100.0, true));

            Goods foundGoods = goodsService.getGoodsById(goods.getId());

            assertEquals(goods.getId(), foundGoods.getId());
        }

        @Test
        void testGetGoodsByIdNotFound() {
            assertThrows(GoodsNotFoundException.class, () -> goodsService.getGoodsById(999L));
        }

        @Test
        void testUpdateGoods() {
            Goods goods = goodsService.createGoods(new Goods(null, "Old Name", "Old Description", 50.0, false));
            Goods updatedGoods = new Goods(null, "New Name", "New Description", 100.0, true);

            Goods result = goodsService.updateGoods(goods.getId(), updatedGoods);

            assertEquals("New Name", result.getName());
            assertEquals("New Description", result.getDescription());
            assertEquals(100.0, result.getPrice());
            assertTrue(result.isInStock());
        }

        @Test
        void testDeleteGoods() {
            Goods goods = goodsService.createGoods(new Goods(null, "Test Goods", "Description", 100.0, true));
            goodsService.deleteGoods(goods.getId());

            assertThrows(GoodsNotFoundException.class, () -> goodsService.getGoodsById(goods.getId()));
        }
}
