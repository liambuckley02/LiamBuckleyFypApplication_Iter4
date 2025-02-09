//Below code is generate with the help of GitHub Copilot and SendGrid API that I set up and is modified to fit my project
package com.example.liambuckleyfyp.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridEmailService {

    // Injects the SendGrid API key from the application properties
    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    // Method to send a booking confirmation email
    public void sendBookingConfirmation(String to, String subject, String text) throws IOException {
        // Creates the sender email object
        Email from = new Email("121421742@umail.ucc.ie"); // Use your verified email address
        // Creates the recipient email object
        Email toEmail = new Email(to);
        // Creates the email content object
        Content content = new Content("text/plain", text);
        // Creates the email object with the sender, subject, recipient, and content
        Mail mail = new Mail(from, subject, toEmail, content);

        // Initializes the SendGrid client with the API key
        SendGrid sg = new SendGrid(sendGridApiKey);
        // Creates a request object to send the email
        Request request = new Request();
        try {
            // Sets the request method to POST
            request.setMethod(Method.POST);
            // Sets the endpoint to "mail/send"
            request.setEndpoint("mail/send");
            // Sets the request body to the built email object
            request.setBody(mail.build());
            // Sends the email using the SendGrid API
            sg.api(request);
        } catch (IOException ex) {
            // Rethrows the IOException if an error occurs
            throw ex;
        }
    }
}

//References
//GitHub Co-pilot
//License to: Liam Buckley(liambuckley02)
//License restriction: For educational use only
//Valid through: November 2nd 2025
//SendGrid (2021). SendGrid Documentation. [online] SendGrid. Available at: https://sendgrid.com/docs/API_Reference/api_v3.html [Accessed 2 Nov. 2024].