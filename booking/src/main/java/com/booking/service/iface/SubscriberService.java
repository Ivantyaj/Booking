package com.booking.service.iface;

import com.booking.model.entity.Subscriber;

import java.util.List;

public interface SubscriberService {
    Subscriber save(Subscriber subscriber);

    void deleteById(Long id);

    Subscriber findByEmail(String email);

    void deleteByEmail(String email);

    Subscriber findById(Long id);

    List<Subscriber> findAll();
}
