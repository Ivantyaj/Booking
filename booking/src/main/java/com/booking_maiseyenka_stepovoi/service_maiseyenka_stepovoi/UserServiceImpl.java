package com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi;

import com.booking_maiseyenka_stepovoi.exceptions_maiseyenka_stepovoi.NotFoundException;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.entity_maiseyenka_stepovoi.User;
import com.booking_maiseyenka_stepovoi.model_maiseyenka_stepovoi.repository_maiseyenka_stepovoi.UserRepository;
import com.booking_maiseyenka_stepovoi.service_maiseyenka_stepovoi.iface_maiseyenka_stepovoi.UserService;
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
