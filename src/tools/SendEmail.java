package tools;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class SendEmail {


    public static void sendMail(String recepient,String topic,String content) throws MessagingException {
        System.out.println("preparing to send email");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "managementevent21@gmail.com";
        String password = "mgmt1234";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail,recepient,topic,content);

        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    


    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String topic,String content) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
        message.setSubject(topic);
        message.setText(content);
        return message;
    }

}