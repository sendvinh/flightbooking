/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.service;

import iviettech.project.flightbooking.entity.BankAccount;
import iviettech.project.flightbooking.entity.Booking;
import iviettech.project.flightbooking.helper.TextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendSuccessBookingEmail(Booking booking) {
        String content = TextHelper.createBookingInfo(booking);
        String emailSubject = TextHelper.createEmailSubjectForBooking();
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(booking.getCustomer().getEmail());
        email.setSubject(emailSubject);
        email.setText(content);
        mailSender.send(email);
    }
    
    public void testEmail() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("toitenla@mailinator.com");
        email.setSubject("TEST SUBJECT");
        email.setText("TEST CONTENT");
        mailSender.send(email);
    }
}
