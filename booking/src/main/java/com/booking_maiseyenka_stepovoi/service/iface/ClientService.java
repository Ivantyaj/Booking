package com.booking_maiseyenka_stepovoi.service.iface;

import com.booking_maiseyenka_stepovoi.model.entity.Client;

import java.util.List;

public interface ClientService {
    void save(Client object);

    Client getById(Long id);

    Client findByEmailAndPhone(String email, String phone);

    List<Client> getAll();
}
