package com.booking.service;

import com.booking.exceptions.NotFoundException;
import com.booking.model.entity.Client;
import com.booking.model.repository.ClientRepository;
import com.booking.service.iface.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public void save(Client object) {
        clientRepository.save(object);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Клиент не найден")
        );
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

}
