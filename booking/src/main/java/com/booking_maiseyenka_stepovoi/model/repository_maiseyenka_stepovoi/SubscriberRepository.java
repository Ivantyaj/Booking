package com.booking_maiseyenka_stepovoi.model.repository_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Optional<Subscriber> findByEmail(String email);

    void deleteByEmail(String email);
}
