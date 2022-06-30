package com.bridglabz.bookstoreapps.model;

import com.bridglabz.bookstoreapps.dto.UserRegistrationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_registration")
public class UserRegistrationData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private LocalDate dob;
    private LocalDate registerDate;
    private Boolean verify;
    private LocalDate updatedDate;


    public void createUser(UserRegistrationDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();
        this.dob = userDTO.getDob();
        this.registerDate = userDTO.getRegisterDate();
        this.verify = userDTO.getVerify();
    }

    public void updateUser(UserRegistrationDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();
        this.dob = userDTO.getDob();
        this.registerDate = userDTO.getRegisterDate();
        this.verify = userDTO.getVerify();
        this.updatedDate = userDTO.getUpdatedDate();
    }
}