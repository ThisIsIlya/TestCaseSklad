package com.sklad.testCase.controller;

import com.sklad.testCase.entity.Delivery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DeliveryControllerInterface {

    List<Delivery> getAllDeliveries();

    ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id);

    ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery);

    ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery);

    ResponseEntity<Void> deleteDelivery(@PathVariable Long id);
}
