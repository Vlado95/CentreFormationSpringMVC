package com.cefisi.registration.listener;

import com.cefisi.modeles.Personne;
import java.util.UUID;

import com.cefisi.registration.OnRegistrationCompleteEvent;
import com.cefisi.service.IUserService;
import com.cefisi.validation.EnvoiMail;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private IUserService service;

    @Autowired
    private MessageSource messages;

    // @Autowired
//    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    private Properties mailServerProperties = System.getProperties();
    private static String adresse_gmail = "tvradmir@gmail.com";
    private static String mdp = "kadoudou";

    // APIlscl
    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        try {
            System.out.println("com.cefisi.registration.listener.RegistrationListener.onApplicationEvent() debut");
            this.confirmRegistration(event);
            System.out.println("com.cefisi.registration.listener.RegistrationListener.onApplicationEvent() fin");
        } catch (MessagingException ex) {
            System.out.println("com.cefisi.registration.listener.RegistrationListener.onApplicationEvent()" +ex.getLocalizedMessage());
            Logger.getLogger(RegistrationListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void confirmRegistration(final OnRegistrationCompleteEvent event) throws MessagingException {
//        final Personne user = event.getUser();
//        final String token = UUID.randomUUID().toString();
//        service.createVerificationTokenForUser(user, token);
//
//        //final SimpleMailMessage email = constructEmailMessage(event, user, token);
//        String adresse = "tvradmir@yahoo.fr";
//         String object ="Validation d'inscription";
//         String message = "Veuillez valider votre inscription en cliquant sur ce lien \n" + "\n<a href='http://localhost:8084/CentreFormationSpringMVC'>Test.com</a>";
//         EnvoiMail.envoyer(adresse, object, message);
//        //mailSender.send(email);
//    }
    private void confirmRegistration(final OnRegistrationCompleteEvent event) throws MessagingException {
        final Personne user = event.getUser();
        System.out.println("com.cefisi.registration.listener.RegistrationListener.confirmRegistration()"+user.getIdPersonne());
        final String token = UUID.randomUUID().toString();
        try {
            service.createVerificationTokenForUser(user, token); 
        } catch (Exception e) {
            System.out.println("err enregistration token: "+e.getLocalizedMessage());
        }
       

        System.out
                .println("\n 1st ===> Mise en place des propriétés du serveur");

        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out
                .println("Les propriétés du serveur de messagerie ont été mis en place avec succès ...");

        final Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);

//        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        final MimeMessage email = constructEmailMessage(event, user, token, getMailSession);
//        mailSender.send(email);
        Transport transport = getMailSession.getTransport("smtp");

        // Entrer UserID and Password (XXXxxxxxxxx@gmail.com)
        transport.connect("smtp.gmail.com", adresse_gmail, mdp);

        transport.sendMessage(email,email.getAllRecipients());

        transport.close();
        System.out.println("\n\n ===> mail envoyé");
    }

    //
//    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final Personne user, final String token) {
//        final String recipientAddress = user.getEmail();
//        final String subject = "Registration Confirmation";
//        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
//        final String message = messages.getMessage("Veuillez valider votre inscription en cliquant sur ce lien ", null, event.getLocale());
//        final SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " \r\n" + confirmationUrl);
//        email.setFrom("tvradmir@gmail.com");
//        return email;
//    }
    private final MimeMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final Personne user, final String token, final Session getMailSession) throws MessagingException {

        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
        final String message = messages.getMessage(/*"Veuillez valider votre inscription en cliquant sur ce lien "*/"message.regSucc", null, event.getLocale());

//        System.out
//                .println("\n 1st ===> Mise en place des propriétés du serveur");
//        
//        mailServerProperties.put("mail.smtp.port", "587");
//        mailServerProperties.put("mail.smtp.auth", "true");
//        mailServerProperties.put("mail.smtp.starttls.enable", "true");
//        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        System.out
//                .println("Les propriétés du serveur de messagerie ont été mis en place avec succès ...");
        // 2
        System.out
                .println("\n\n 2nd ===> ouverture de la session mail et envoi");
//        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.setHeader("Content-Type", "text/html; charset=utf-8");

        generateMailMessage.addRecipient(Message.RecipientType.TO,
                new InternetAddress(recipientAddress));

        // Mail
        generateMailMessage.setContent(message + " \r\n" + confirmationUrl, "text/html; charset=utf-8");
        generateMailMessage.setSubject(subject);
        System.out.println("La session a été créée avec succès ..");

//        Transport transport = getMailSession.getTransport("smtp");
//
//        // Entrer UserID and Password (XXXxxxxxxxx@gmail.com)
//        transport.connect("smtp.gmail.com", adresse_gmail, mdp);
//        // final String recipientAddress = user.getEmail();
//        final String subject = "Registration Confirmation";
//        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
//        final String message = messages.getMessage("Veuillez valider votre inscription en cliquant sur ce lien ", null, event.getLocale());
//        //final Message email = new new MimeMessage(session);
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " \r\n" + confirmationUrl);
//        email.setFrom("tvradmir@gmail.com");
        return generateMailMessage;

        /*
        // 1
        System.out
                .println("\n 1st ===> Mise en place des propriétés du serveur");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        System.out
                .println("Les propriétés du serveur de messagerie ont été mis en place avec succès ...");

        // 2
        System.out
                .println("\n\n 2nd ===> ouverture de la session mail et envoi");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.setHeader("Content-Type", "text/html; charset=utf-8");

        generateMailMessage.addRecipient(Message.RecipientType.TO,
                new InternetAddress(adresseTo));

        // Mail
        generateMailMessage.setContent(messageText, "text/html; charset=utf-8");
        generateMailMessage.setSubject(objet);
        System.out.println("La session a été créée avec succès ..");

        Transport transport = getMailSession.getTransport("smtp");

        // Entrer UserID and Password (XXXxxxxxxxx@gmail.com)
        transport.connect("smtp.gmail.com", adresse_gmail, mdp);

        transport.sendMessage(generateMailMessage,
                generateMailMessage.getAllRecipients());

        transport.close();
        System.out.println("\n\n ===> mail envoyé");
         */
    }

    //
//    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final Personne user, final String token) {
//        final String recipientAddress = user.getEmail();
//        final String subject = "Registration Confirmation";
//        final String confirmationUrl = event.getAppUrl() + "/registrationConfirm.html?token=" + token;
//        final String message = messages.getMessage("message.regSucc", null, event.getLocale());
//        final SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " \r\n" + confirmationUrl);
//        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }
}
