package com.bridglabz.bookstoreapps.service;

import com.bridglabz.bookstoreapps.dto.LoginDTO;
import com.bridglabz.bookstoreapps.dto.ResponseDTO;
import com.bridglabz.bookstoreapps.dto.UserRegistrationDTO;
import com.bridglabz.bookstoreapps.exception.UserRegistrationException;
import com.bridglabz.bookstoreapps.model.UserRegistrationData;
import com.bridglabz.bookstoreapps.repository.UserRegistrationRepository;
import com.bridglabz.bookstoreapps.tokenutil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationService implements IUserRegistrationService{
    @Autowired
    private UserRegistrationRepository userRepo;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * This method creates user registration with new user data
     * @param userDTO
     * @return ResponseDto
     */
    @Override
    public ResponseDTO createUserData(UserRegistrationDTO userDTO) {

        Optional<UserRegistrationData> isUserPresent = userRepo.findByEmailId(userDTO.getEmailId());
        if (isUserPresent.isPresent()) {
            throw new UserRegistrationException("User Already Exists.");
        } else {
            UserRegistrationData userData = new UserRegistrationData();
            userData.createUser(userDTO);
            userRepo.save(userData);
            return new ResponseDTO("User Created Successfully", userData);
        }
    }

    /**
     * This method shows all user data from user repository
     * @return list of users data
     */
    @Override
    public List<UserRegistrationData> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * This method shows user data by taking user id
     * @param userId
     * @return UserData
     */
    @Override
    public UserRegistrationData getUserDataById(int userId) {
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(userId);
        if (isUserPresent.isPresent()) {
            return userRepo.findById(userId).orElseThrow(() -> new UserRegistrationException("User Id Not Found"));
        }
        else {
            throw new UserRegistrationException("Invalid Id");
        }
    }

    /**
     * This method updates user data by user id
     * @param userId
     * @param userDTO
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO updateUserData(int userId, UserRegistrationDTO userDTO) {
        UserRegistrationData userData = this.getUserDataById(userId);

        Optional<UserRegistrationData> isUserPresent = userRepo.findById(userId);
        if (isUserPresent.isPresent()) {
            userData.updateUser(userDTO);
            userRepo.save(userData);
            return new ResponseDTO("User Details Successfully Updated", userData);
        }
        else {
            throw new UserRegistrationException("User is not present");
        }
    }

    /**
     * This method deletes user data by user id
     * @param userId
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO deleteUserData(int userId) {
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(userId);

        if (isUserPresent.isPresent()) {
            userRepo.deleteById(userId);
            return new ResponseDTO("User Successfully Deleted", "Deleted User Id : " + userId);
        }
        else {
            throw new UserRegistrationException("User is not Present!");
        }
    }

    /**
     * This method validates login credentials
     * @param loginDTO
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO loginValidation(LoginDTO loginDTO) {
        String token;
        Optional<UserRegistrationData> isUserPresent = userRepo.findByEmailId(loginDTO.getEmailId());

        if (isUserPresent.isPresent()) {
            String password = isUserPresent.get().getPassword();
            if (password.equals(loginDTO.getPassword())) {
                token = tokenUtil.createToken(isUserPresent.get().getUserId());
                return new ResponseDTO("User is Found", token);
            } else throw new UserRegistrationException("Password is Wrong");
        }
        else {
            throw new UserRegistrationException("Email Id or Password is Wrong");
        }
    }

    /**
     * This method verifies user by token
     * @param token
     * @return boolean
     */
    @Override
    public Boolean verifyUser(String token) {
        int id = tokenUtil.decodeToken(token);

        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setVerify(true);
            userRepo.save(isUserPresent.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method recovers password if user forgets
     * @param emailId
     * @param password
     * @return User Data
     */
    @Override
    public UserRegistrationData forgotPassword(String emailId, String password) {
        Optional<UserRegistrationData> isUserPresent = userRepo.findByEmailId(emailId);
        if (isUserPresent.isPresent()) {
            isUserPresent.get().setPassword(password);
            return userRepo.save(isUserPresent.get());
        } else {
            throw new UserRegistrationException("Invalid Email");
        }
    }
}