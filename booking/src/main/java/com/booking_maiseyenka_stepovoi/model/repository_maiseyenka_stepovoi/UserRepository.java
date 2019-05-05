package com.booking_maiseyenka_stepovoi.model.repository_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginAndPassword(String login, String password);
}
