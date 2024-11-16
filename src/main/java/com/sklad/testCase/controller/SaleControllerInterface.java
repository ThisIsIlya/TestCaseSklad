package com.sklad.testCase.controller;

import com.sklad.testCase.entity.Sale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface SaleControllerInterface {

    List<Sale> getAllSales();

    ResponseEntity<Sale> getSaleById(@PathVariable Long id);

    ResponseEntity<Sale> createSale(@RequestBody Sale sale);

    ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale sale);

    ResponseEntity<Void> deleteSale(@PathVariable Long id);
}
