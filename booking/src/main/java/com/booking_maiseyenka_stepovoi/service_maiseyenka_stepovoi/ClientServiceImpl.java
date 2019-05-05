package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.exceptions_maiseyenka_stepovoi.NotFoundException;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi.Client;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.repository_maiseyenka_stepovoi.ClientRepository;
import com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi.ClientService;
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
    public Client findByEmailAndPhone(String email, String phone) {
        return clientRepository.findByEmailAndPhone(email, phone).orElse(new Client(email,phone));
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

}
