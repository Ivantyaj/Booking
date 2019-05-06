package com.booking_maiseyenka_stepovoi.model.repository;

import com.booking_maiseyenka_stepovoi.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmailAndPhone(String email, String phone);
}
