package com.booking_maiseyenka_stepovoi.service;

import com.booking_maiseyenka_stepovoi.exceptions.NotFoundException;
import com.booking_maiseyenka_stepovoi.model.entity.User;
import com.booking_maiseyenka_stepovoi.model.repository.UserRepository;
import com.booking_maiseyenka_stepovoi.service.iface.UserService;
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

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
