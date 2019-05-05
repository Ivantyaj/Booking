package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi.User;

public interface UserService {
    User findByLoginAndPassword(String login, String password);

    User save(User user);
}
