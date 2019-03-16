package com.booking.service.iface;

import com.booking.model.entity.Client;

import java.util.List;
public interface ClientService {
    void save(Client object);

    Client getById(Long id);

    List<Client> getAll();
}
