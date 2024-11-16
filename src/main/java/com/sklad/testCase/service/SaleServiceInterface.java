package com.sklad.testCase.service;

import com.sklad.testCase.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleServiceInterface {

    List<Sale> getAllSales();

    Optional<Sale> getSaleById(Long id);

    Sale createSale(Sale sale);

    Sale updateSale(Long id, Sale sale);

    void deleteSale(Long id);
}
