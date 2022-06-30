package com.bridglabz.bookstoreapps.controller;

import com.bridglabz.bookstoreapps.email.EmailMessageModel;
import com.bridglabz.bookstoreapps.email.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailController {

    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessageModel emailMessage) {
        this.emailSenderService.sendEmail(emailMessage.getToEmail(), emailMessage.getSubject(), emailMessage.getBody());
        return ResponseEntity.ok("Success");
    }
}