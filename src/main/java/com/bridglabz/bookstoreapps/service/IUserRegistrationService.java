package com.bridglabz.bookstoreapps.service;

import com.bridglabz.bookstoreapps.dto.LoginDTO;
import com.bridglabz.bookstoreapps.dto.ResponseDTO;
import com.bridglabz.bookstoreapps.dto.UserRegistrationDTO;
import com.bridglabz.bookstoreapps.model.UserRegistrationData;

import java.util.List;

public interface IUserRegistrationService {
    public ResponseDTO createUserData(UserRegistrationDTO userDTO);

    public List<UserRegistrationData> getAllUsers();

    public ResponseDTO updateUserData(int userId, UserRegistrationDTO userDTO);

    public ResponseDTO deleteUserData(int userId);

    public UserRegistrationData getUserDataById(int userId);

    public ResponseDTO loginValidation(LoginDTO loginDTO);

    public Boolean verifyUser(String token);

    UserRegistrationData forgotPassword(String emailId, String password);

}