package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.exceptions_maiseyenka_stepovoi.NotFoundException;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi.Subscriber;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.repository_maiseyenka_stepovoi.SubscriberRepository;
import com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi.SubscriberService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public void deleteById(Long id) {
        subscriberRepository.deleteById(id);
    }

    @Override
    public Subscriber findByEmail(String email) {
        return subscriberRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("Подписчика с таким email адресом не найдено"));
    }

    @Override
    public void deleteByEmail(String email) {
        subscriberRepository.deleteByEmail(email);
    }

    @Override
    public Subscriber findById(Long id) {
        return subscriberRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Подписчик не найден"));
    }

    @Override
    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }


}
