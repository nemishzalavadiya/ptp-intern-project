package com.pirimidtech.ptp.repository;

import com.pirimidtech.ptp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
