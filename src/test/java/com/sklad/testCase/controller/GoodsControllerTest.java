package com.sklad.testCase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklad.testCase.entity.Goods;
import com.sklad.testCase.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GoodsController.class)
public class GoodsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GoodsService goodsService;

    @Test
    void testGetAllGoods() throws Exception {

        when(goodsService.getAllGoods()).thenReturn(List.of(
                new Goods(1L, "Goods 1", "Description 1", 50.0, true),
                new Goods(2L, "Goods 2", "Description 2", 150.0, false)
        ));

        mockMvc.perform(get("/api/goods"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateGoods() throws Exception {
        Goods goods = new Goods(null, "New Goods", "Description", 200.0, true);

        when(goodsService.createGoods(Mockito.any(Goods.class))).thenReturn(
                new Goods(1L, "New Goods", "Description", 200.0, true)
        );

        mockMvc.perform(post("/api/goods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goods)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteGoods() throws Exception {
        mockMvc.perform(delete("/api/goods/1"))
                .andExpect(status().isNoContent());
    }
}
