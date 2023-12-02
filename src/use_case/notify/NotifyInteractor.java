package use_case.notify;


import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotifyInteractor implements NotifyInputBoundary {
    final NotifyOutputBoundary notifyPresenter;
    final NotifyUserDataAccessInterface userDataAccessObject;

    public NotifyInteractor(NotifyOutputBoundary notifyPresenter, NotifyUserDataAccessInterface userDataAccessObject) {
        this.notifyPresenter = notifyPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(NotifyInputData notifyInputData) {
        String message = notifyInputData.getMessage();
        Map<String,String> userEmails = userDataAccessObject.getUsersAndEmails();

        boolean isEmailSentSuccessfully = true;
        ArrayList<String> errorUser = new ArrayList<>();
        for (Map.Entry<String, String> entry : userEmails.entrySet()) {
            String email = entry.getKey(); // This is the first component (the email)
            String name = entry.getValue();
            ;
            boolean success = sendEmail(message, email);
            if (!success) {
                isEmailSentSuccessfully = false;
                errorUser.add(name);
                // Optionally, you can break the loop if one failure is enough to consider the whole operation failed
                // break;

            }
        }
        NotifyOutputData notifyOutputData = new NotifyOutputData(errorUser);
        if (isEmailSentSuccessfully) {
            notifyPresenter.prepareSuccessView();
        } else {
            notifyPresenter.prepareFailedView(notifyOutputData);
        }
    }

    private boolean sendEmail(String messageContent, String Email) {
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
            message.setSubject("Notification"); // Set Subject
            message.setText(messageContent); // Set the message content

            // Send the message
            Transport.send(message, "DeclanPang@126.com", "HLPKWQXIIJLOJFLQ"); // Your email and password
            System.out.println("Email sent successfully.");
            return true;
        } catch (MessagingException mex) {
            System.out.println("Email failed to sent");
            mex.printStackTrace();
            return false;
        }
    }
}
