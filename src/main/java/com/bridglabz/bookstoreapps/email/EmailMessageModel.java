package com.bridglabz.bookstoreapps.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageModel {
    private String toEmail;
    private String subject;
    private String body;

}
