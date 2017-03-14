package com.cefisi.validation;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoiMail {

    private static Properties mailServerProperties;
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;
    private static String adresse_gmail = "tvradmir@gmail.com";
    private static String mdp ="kadoudou";

  /*  public static String getAdresse_gmail() {
        return adresse_gmail;
    }

    public static void setAdresse_gmail(String adresse_gmail) {
        EnvoiMail.adresse_gmail = adresse_gmail;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        EnvoiMail.mdp = mdp;
    }

    /**
     * Initialisation des données de connection pour les expeditions de mail :
     * adresse mail avec mot de passe du compte gmail
     *
     * @param adresse_gmail
     * @param mdp
     */
   /* public void initialisation(String adresse_gmail, String mdp) {

        setAdresse_gmail(adresse_gmail);
        setMdp(mdp);
    }
*/
    /*
	 * Envoi le mail préparé
     */
    public static void envoyer(String adresseTo, String objet,
            String messageText) throws/* AddressException ,*/ MessagingException {

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
    }

    public static void sendMail(String adresseTo, String objet,
            String messageText) throws AddressException, MessagingException {
        // 1
        System.out
                .println("\n 1st ===> Mise en place des propriétés du serveur");
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(mailServerProperties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tvradmir@gmail.com", "kadoudou");
            }
        });
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("no-reply@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("tvradmir@yahoo.fr"));
        message.setSubject("Testing Subject");
        message.setText("Dear Mail Crawler,"
                + "\n\n No spam to my email, please!");

        Transport.send(message);

        System.out.println("Done");
    }

}
