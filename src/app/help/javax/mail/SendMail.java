package app.help.javax.mail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void main(String[] args) {
        // Set up the SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.126.com"); // SMTP Server Address
        properties.put("mail.smtp.port", "465"); // SMTP Port Number for SSL
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Create a session with the specified properties
        Session session = Session.getInstance(properties);

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress("DeclanPang@126.com")); // Your email address

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("pangtl@126.com")); // Recipient's email address

            // Set Subject: header field
            message.setSubject("Your Subject Here");

            // Set the actual message
            message.setText("This is the email message body.");

            // Send the message
            Transport.send(message, "DeclanPang@126.com", "HLPKWQXIIJLOJFLQ"); // Your email and password
            System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }


}