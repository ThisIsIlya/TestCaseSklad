package com.sklad.testCase.service;

import com.sklad.testCase.entity.Goods;
import com.sklad.testCase.repository.GoodsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoodsServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsService goodsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGoods() {
        Goods goods1 = new Goods(1L, "Goods 1", "Description 1", 100.0, true);
        Goods goods2 = new Goods(2L, "Goods 2", "Description 2", 200.0, false);

        when(goodsRepository.findAll()).thenReturn(Arrays.asList(goods1, goods2));

        var result = goodsService.getAllGoods();
        assertEquals(2, result.size());
        assertEquals("Goods 1", result.get(0).getName());
        assertEquals("Goods 2", result.get(1).getName());

        verify(goodsRepository, times(1)).findAll();
    }

    @Test
    void testGetGoodsById() {
        Goods goods = new Goods(1L, "Goods 1", "Description 1", 100.0, true);

        when(goodsRepository.findById(1L)).thenReturn(Optional.of(goods));

        var result = goodsService.getGoodsById(1L);
        assertTrue(result.isPresent());
        assertEquals("Goods 1", result.get().getName());

        verify(goodsRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateGoods() {
        Goods goods = new Goods(null, "Goods 1", "Description 1", 100.0, true);
        Goods savedGoods = new Goods(1L, "Goods 1", "Description 1", 100.0, true);

        when(goodsRepository.save(goods)).thenReturn(savedGoods);

        var result = goodsService.createGoods(goods);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Goods 1", result.getName());

        verify(goodsRepository, times(1)).save(goods);
    }

    @Test
    void testUpdateGoods() {
        Goods existingGoods = new Goods(1L, "Goods 1", "Description 1", 100.0, true);
        Goods updatedGoods = new Goods(null, "Updated Goods", "Updated Description", 150.0, false);
        Goods savedGoods = new Goods(1L, "Updated Goods", "Updated Description", 150.0, false);

        when(goodsRepository.findById(1L)).thenReturn(Optional.of(existingGoods));
        when(goodsRepository.save(any(Goods.class))).thenReturn(savedGoods);

        var result = goodsService.updateGoods(1L, updatedGoods);
        assertNotNull(result);
        assertEquals("Updated Goods", result.getName());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(150.0, result.getPrice());

        verify(goodsRepository, times(1)).findById(1L);
        verify(goodsRepository, times(1)).save(any(Goods.class));
    }

    @Test
    void testDeleteGoods() {
        doNothing().when(goodsRepository).deleteById(1L);

        goodsService.deleteGoods(1L);

        verify(goodsRepository, times(1)).deleteById(1L);
    }
}
