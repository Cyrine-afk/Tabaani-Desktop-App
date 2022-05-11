/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabaani.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import tabaani.gui.ADDeventController;

/**
 *
 * @author DELL
 */
public class JavaMailUtil {
    public static void notificationShow() {
        Image img = new Image("images/reverifier.png");
                Notifications notificationBuilder = Notifications.create()
                        .title("Email Sent")
                        .text("Email successfully sent to user !")
                        .graphic(new ImageView(img)/*null*/)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER)
                        .onAction((ActionEvent event1) -> {System.out.println("Clicked on notification");});
                notificationBuilder.darkStyle();
                notificationBuilder.show();
    }
    
    public static void sendMail(String recepient) throws Exception{
        System.out.println("Preparing to send email...");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "cyrine1409@gmail.com";
        String password = "xo1xo2fu3";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication (myAccountEmail,password);
            }
        });
        
        Message message = prepareMessage(session, myAccountEmail, recepient);
        Transport.send(message);
        
        System.out.println("Email sent !");
        notificationShow();
        
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Tabaani event alert");
            //message.setText("Hello Tabaani User, \n Something is wrong with the event you're trying to host, please contact us on : 96069516 to get that checked. \n Greetings, \n Tabaani Team.");
            String htmlCode ="<body style=\"background-color:#fdffc4;\">"
                    + "<h1 style=\"color:#56a1cb\";> TABAANI NOTIFICATION EMAIL <h1> </br> "
                    + "<h5> <b> Hello Tabaani User, </b> </h5>"
                    + "<h5> <b> Something is wrong with the event you're trying to host, please contact us on : 96069516 to get that checked. </b> </h5> "
                    + "<h5> <b> Greetings,</b> </h5>"
                    + "<h5> <b>Tabaani Team. </b> </h5>"
                    + "</body>";
            message.setContent(htmlCode, "text/html");
                    
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ADDeventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
