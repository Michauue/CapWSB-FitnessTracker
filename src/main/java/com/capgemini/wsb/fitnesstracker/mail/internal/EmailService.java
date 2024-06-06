package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender {

    @Autowired
    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(EmailDto email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.toAddress());
        simpleMailMessage.setSubject(email.subject());
        simpleMailMessage.setText(email.content());
        simpleMailMessage.setFrom("YourFitnessApp");
        javaMailSender.send(simpleMailMessage);
    }
}
