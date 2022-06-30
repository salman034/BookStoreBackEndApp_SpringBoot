package com.bridglabz.bookstoreapps.controller;

import com.bridglabz.bookstoreapps.dto.LoginDTO;
import com.bridglabz.bookstoreapps.dto.ResponseDTO;
import com.bridglabz.bookstoreapps.dto.UserRegistrationDTO;
import com.bridglabz.bookstoreapps.email.IEmailSenderService;
import com.bridglabz.bookstoreapps.model.UserRegistrationData;
import com.bridglabz.bookstoreapps.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userregistration")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userService;

    @Autowired
    private IEmailSenderService emailService;

    /**
     * Ability to show all user details in user repository
     * @return ResponseEntity
     */
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        List<UserRegistrationData> userData = userService.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("Get Call Successfully Done", userData);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to show user data by user id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getUserDataById(@PathVariable("id") int id) {
        UserRegistrationData userData = userService.getUserDataById(id);
        ResponseDTO responseDTO = new ResponseDTO("Get By Id Call Successfully Done", userData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to register new user and sending mail of registration
     * @param userDTO
     * @return ResponseEntity
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createUserData(@RequestBody UserRegistrationDTO userDTO) {
        ResponseDTO responseDTO = userService.createUserData(userDTO);
        emailService.sendEmail(userDTO.getEmailId(), "User Registered Successfully.",
                "Dear " + userDTO.getFirstName() + ", You have Successfully registered with book store.");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to update user details by user id
     * @param id
     * @param userDTO
     * @return ResponseEntity
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateUserDataById(@PathVariable("id") int id,
                                                          @RequestBody UserRegistrationDTO userDTO) {
        ResponseDTO responseDTO = userService.updateUserData(id, userDTO);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to delete user details by user id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUserData(@PathVariable("id") int id) {
        ResponseDTO responseDTO = userService.deleteUserData(id);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to login by user id and password by validating
     * @param loginDTO
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = userService.loginValidation(loginDTO);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to verify user by token
     * @param token
     * @return ResponseEntity
     */
    @PostMapping("/user/verify/{token}")
    public ResponseEntity<ResponseDTO> loginVerify(@PathVariable("token")String token) {
        Boolean result = userService.verifyUser(token);
        ResponseDTO responseDTO = new ResponseDTO("User Successfully Verified", "Verification Status : "
                + result);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to recover password using email id if user forgets the password
     * @param emailId
     * @param password
     * @return ResponseEntity
     */
    @PostMapping("/forgotpassword")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestParam String emailId,
                                                      @RequestParam String password) {
        UserRegistrationData userData = userService.forgotPassword(emailId, password);
        ResponseDTO responseDTO = new ResponseDTO("Password Updated Successfully", userData);
        emailService.sendEmail(userData.getEmailId(), "Password Recovered Successfully.",
                "Dear " + userData.getFirstName() + ", You have Successfully recovered password of book store.");
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

}