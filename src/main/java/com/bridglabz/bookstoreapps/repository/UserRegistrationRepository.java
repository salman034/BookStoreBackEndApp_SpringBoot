package com.bridglabz.bookstoreapps.repository;

import com.bridglabz.bookstoreapps.model.UserRegistrationData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationData, Integer> {
    public Optional<UserRegistrationData> findByEmailId(String emailId);
}
