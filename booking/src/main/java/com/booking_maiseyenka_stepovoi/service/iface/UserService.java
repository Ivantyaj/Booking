package com.booking_maiseyenka_stepovoi.service.iface;

import com.booking_maiseyenka_stepovoi.model.entity.User;

public interface UserService {
    User findByLoginAndPassword(String login, String password);

    User save(User user);
}
