package com.bridglabz.bookstoreapps.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private Boolean verify=false;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate registerDate = LocalDate.now();
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedDate = LocalDate.now();

}