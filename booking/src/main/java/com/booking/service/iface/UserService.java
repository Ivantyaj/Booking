package com.booking.service.iface;

import com.booking.model.entity.User;

public interface UserService {
    User findByLoginAndPassword(String login, String password);

    User save(User user);
}
