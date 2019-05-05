package com.booking_maiseyenka_stepovoi.service.iface_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi.Subscriber;

import java.util.List;

public interface SubscriberService {
    Subscriber save(Subscriber subscriber);

    void deleteById(Long id);

    Subscriber findByEmail(String email);

    void deleteByEmail(String email);

    Subscriber findById(Long id);

    List<Subscriber> findAll();
}