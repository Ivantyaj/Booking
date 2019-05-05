package com.booking_maiseyenka_stepovoi.service.iface_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.model.entity_maiseyenka_stepovoi.User;

public interface UserService {
    User findByLoginAndPassword(String login, String password);

    User save(User user);
}
