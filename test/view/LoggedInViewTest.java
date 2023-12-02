package view;

import org.junit.Before;
import org.junit.Test;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.summary.SummaryController;
import interface_adapter.save.SaveController;
import interface_adapter.translate.TranslateController;
import interface_adapter.notify.NotifyController;
import interface_adapter.notify.NotifyViewModel;
import use_case.logout.LogoutInputBoundary;
import use_case.notify.NotifyInputBoundary;
import use_case.notify.NotifyInputData;
import use_case.save.SaveInputBoundary;
import use_case.save.SaveInputData;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import use_case.summary.SummaryInputBoundary;
import use_case.summary.SummaryInputData;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInputData;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.net.Socket;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoggedInViewTest {

    private LoggedInViewModel loggedInViewModel;
    private NotifyViewModel notifyViewModel;
    private LoggedInView loggedInView;
    private JTextField messageInputField;
    private JButton sendButton;
    private JButton notifyButton;
    private JButton logOutButton;
    private JButton summaryButton;
    private JButton saveButton;
    private JCheckBox translateCheckBox;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        loggedInViewModel = new LoggedInViewModel();
        notifyViewModel = new NotifyViewModel();
        LogoutController logoutController = new LogoutController(new LogoutInputBoundary() {
            @Override
            public void execute() {

            }
        });
        SendMessageController sendMessageController = new SendMessageController(new SendMessageInputBoundary() {
            @Override
            public void execute(SendMessageInputData sendMessageInputData) {

            }
        });
        TranslateController translateController = new TranslateController(new TranslateInputBoundary() {
            @Override
            public void execute(TranslateInputData translateInputData) {

            }
        });
        NotifyController notifyController = new NotifyController(new NotifyInputBoundary() {
            @Override
            public void execute(NotifyInputData notifyInputData) {

            }
        });
        SummaryController summaryController = new SummaryController(new SummaryInputBoundary() {
            @Override
            public void saveSummary(String summary) {

            }

            @Override
            public String getSummary(SummaryInputData summaryInputData) throws Exception {
                return null;
            }
        });
        SaveController saveController = new SaveController(new SaveInputBoundary() {
            @Override
            public void saveMessage(String savedMessage) {

            }

            @Override
            public String getMessage(SaveInputData saveInputData) throws Exception {
                return null;
            }
        });

        loggedInView = new LoggedInView(loggedInViewModel, notifyViewModel, logoutController,
                sendMessageController, translateController, notifyController,
                summaryController, saveController);
        JFrame jf = new JFrame();
        jf.setContentPane(loggedInView);
        jf.pack();
        jf.setVisible(true);

        messageInputField = getField(loggedInView, "messageInputField");
        sendButton = getField(loggedInView, "send");
        notifyButton = getField(loggedInView, "notify");
        logOutButton = getField(loggedInView, "logOut");
        summaryButton = getField(loggedInView, "summary");
        saveButton = getField(loggedInView, "save");
        translateCheckBox = getField(loggedInView, "translate");
    }

    @SuppressWarnings("unchecked")
    private <T> T getField(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    @Test
    public void testMessageSending() throws InterruptedException {
        // Simulate typing in the message input field
        simulateTyping(messageInputField, "Hello, world!");
        assertEquals("Hello, world!", messageInputField.getText());

        // Simulate button click
        sendButton.doClick();
        // ... Add assertions to verify expected behavior after sending the message

        // Test other buttons and interactions similarly
        notifyButton.doClick();
        logOutButton.doClick();
        summaryButton.doClick();
        saveButton.doClick();
        translateCheckBox.doClick();
    }

    private void simulateTyping(JTextComponent component, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            component.setCaretPosition(component.getDocument().getLength());
            KeyEvent keyEvent = new KeyEvent(
                    component, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            component.dispatchEvent(keyEvent);
            sleep(50);
        }
    }
}

