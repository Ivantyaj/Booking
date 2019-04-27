package com.booking.service;

import com.booking.exceptions.NotFoundException;
import com.booking.model.entity.User;
import com.booking.model.repository.UserRepository;
import com.booking.service.iface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElseThrow(() ->
                new NotFoundException("User not found"));
    }
}
