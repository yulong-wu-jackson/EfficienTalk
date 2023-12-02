package view;

import org.junit.Before;
import org.junit.Test;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.clear_users.ClearController;
import interface_adapter.switchtologin.SwitchToLoginController;
import use_case.clear_users.ClearInputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
import use_case.switchtologin.SwitchToLoginInputBoundary;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class SignupViewTest {

    private SignupViewModel signupViewModel;
    private SignupView signupView;
    private JTextField usernameInputField;
    private JTextField emailInputField;
    private JPasswordField passwordInputField;
    private JPasswordField repeatPasswordInputField;
    private JButton signUp;
    private JButton clear;
    private JButton switchToLogin;

    @Before
    public void setUp() {
        signupViewModel = new SignupViewModel();
        SignupController signupController = new SignupController(new SignupInputBoundary() {
            @Override
            public void execute(SignupInputData signupInputData) {
                // Do nothing
            }
        });
        ClearController clearController = new ClearController(new ClearInputBoundary() {
            @Override
            public void execute() {

            }
        });
        SwitchToLoginController switchToLoginController = new SwitchToLoginController(new SwitchToLoginInputBoundary() {
            @Override
            public void execute() {

            }

        });

        signupView = new SignupView(signupController, signupViewModel, clearController, switchToLoginController);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        try {
            usernameInputField = getField(signupView, "usernameInputField");
            emailInputField = getField(signupView, "emailInputField");
            passwordInputField = getField(signupView, "passwordInputField");
            repeatPasswordInputField = getField(signupView, "repeatPasswordInputField");
            signUp = getField(signupView, "signUp");
            clear = getField(signupView, "clear");
            switchToLogin = getField(signupView, "swithToLogin");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T getField(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    @Test
    public void testSignupFunctionality() throws InterruptedException {
        // Simulate typing in various fields
        simulateTyping(usernameInputField, "newUser");
        simulateTyping(emailInputField, "newuser@example.com");
        simulateTyping(passwordInputField, "password123");
        simulateTyping(repeatPasswordInputField, "password123");

        // Assert that the ViewModel is updated
        SignupState currentState = signupViewModel.getState();
        assertEquals("newUser", currentState.getUsername());
        assertEquals("newuser@example.com", currentState.getEmail());
        assertEquals("password123", new String(passwordInputField.getPassword()));
        assertEquals("password123", new String(repeatPasswordInputField.getPassword()));

        // Simulate button clicks
        signUp.doClick();
        clear.doClick();
        switchToLogin.doClick();

        ActionEvent actionEvent = new ActionEvent(signUp, 0, "action");
        signupView.actionPerformed(actionEvent);

        // Add more assertions or interactions as needed
    }

    private void simulateTyping(JTextComponent component, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            component.setCaretPosition(component.getDocument().getLength());
            KeyEvent keyEvent = new KeyEvent(
                    component, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            KeyEvent keyEvent2 = new KeyEvent(
                    component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            KeyEvent keyEvent3 = new KeyEvent(
                    component, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            component.dispatchEvent(keyEvent);
            component.dispatchEvent(keyEvent2);
            component.dispatchEvent(keyEvent3);
            sleep(50);
        }
    }
}

