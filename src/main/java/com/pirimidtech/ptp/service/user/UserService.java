package com.pirimidtech.ptp.service.user;

import com.pirimidtech.ptp.entity.User;
import com.pirimidtech.ptp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;

    public Optional<User> verifyUser(String email, String Password) {
        return userRepository.findByEmailAndPassword(email, Password);
    }
    public Optional<User> getUserById(UUID userId){
        return userRepository.findById(userId);
    }
}
