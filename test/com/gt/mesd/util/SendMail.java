package com.gt.mesd.util;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void main(String[]args) throws IOException {
    	
    	PropertiesToMapConverter mapConverter = new PropertiesToMapConverter();
    	
        final String username = "o89";
        final String password = "Qwer@123";
        
        

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "intraftp.trustmarkins.com");
      //  props.put("mail.smtp.port", "85");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sourabh.verma@trustmarkins.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("sourabh.verma@trustmarkins.com"));
            message.setSubject("Test");
            message.setText("HI");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}