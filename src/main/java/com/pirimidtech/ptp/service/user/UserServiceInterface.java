package com.pirimidtech.ptp.service.user;

import com.pirimidtech.ptp.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface {
    Optional<User> verifyUser(String email, String Password);

    Optional<User> getUserById(UUID userId);
}
