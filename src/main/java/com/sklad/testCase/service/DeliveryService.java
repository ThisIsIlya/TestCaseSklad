package com.sklad.testCase.service;

import com.sklad.testCase.entity.Delivery;
import com.sklad.testCase.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService implements DeliveryServiceInterface{

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Optional<Delivery> updateDelivery(Long id, Delivery delivery) {
        return deliveryRepository.findById(id)
                .map(existingDelivery -> {
                    existingDelivery.setDocumentName(delivery.getDocumentName());
                    existingDelivery.setGoods(delivery.getGoods());
                    existingDelivery.setQuantity(delivery.getQuantity());
                    return deliveryRepository.save(existingDelivery);
                });
    }

    @Override
    public boolean deleteDelivery(Long id) {
        if (deliveryRepository.existsById(id)) {
            deliveryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
