package com.bridglabz.bookstoreapps.email;

public interface IEmailSenderService {
    void sendEmail(String toEmail, String subject, String body);
}
