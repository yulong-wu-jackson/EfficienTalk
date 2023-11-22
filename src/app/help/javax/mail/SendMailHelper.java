package app.help.javax.mail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailHelper {
    private static void sendEmail(String messageContent, String Email) {
        // SMTP server configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.126.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Create a session with the specified properties
        Session session = Session.getInstance(properties);

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("DeclanPang@126.com")); // Your email address
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email)); // Recipient's email address
            message.setSubject("Your Subject Here"); // Set Subject
            message.setText(messageContent); // Set the message content

            // Send the message
            Transport.send(message, "DeclanPang@126.com", "HLPKWQXIIJLOJFLQ"); // Your email and password
            System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String emailMessage = "This is the email message body.";
        String email = "pangtl@126.com";
        sendEmail(emailMessage, email);
    }




}
