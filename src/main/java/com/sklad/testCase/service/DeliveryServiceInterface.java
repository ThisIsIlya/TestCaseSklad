package com.sklad.testCase.service;

import com.sklad.testCase.entity.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryServiceInterface {

    List<Delivery> getAllDeliveries();

    Optional<Delivery> getDeliveryById(Long id);

    Delivery createDelivery(Delivery delivery);

    Optional<Delivery> updateDelivery(Long id, Delivery delivery);

    boolean deleteDelivery(Long id);
}
