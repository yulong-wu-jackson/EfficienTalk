package view;

import org.junit.Before;
import org.junit.Test;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.switchtosignup.SwitchToSignUpController;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.switchtosignup.SwitchToSignUpInputBoundary;
import view.LoginView;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;


public class LoginViewTest {

    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private JTextField usernameInputField;
    private JPasswordField passwordInputField;
    private JTextField groupInputField;
    private JTextField ipAdressInputField;
    private JTextField portInputField;

    @Before
    public void setUp() {
        loginViewModel = new LoginViewModel();
        LoginInputBoundary loginInputBoundary = new LoginInputBoundary() {
            @Override
            public void execute(LoginInputData loginInputData) {
                // Do nothing
            }
        };
        SwitchToSignUpInputBoundary switchToSignUpInputBoundary = new SwitchToSignUpInputBoundary() {
            @Override
            public void execute() {
                // Do nothing
            }
        };
        LoginController loginController = new LoginController(loginInputBoundary);
        SwitchToSignUpController switchToSignUpController = new SwitchToSignUpController(switchToSignUpInputBoundary);

        // Initialize LoginView
        loginView = new LoginView(loginViewModel, loginController, switchToSignUpController);
        JFrame jf = new JFrame();
        jf.setContentPane(loginView);
        jf.pack();
        jf.setVisible(true);

        // Get references to UI components


        try {
            usernameInputField = getField(loginView, "usernameInputField");
            passwordInputField = getField(loginView, "passwordInputField");
            groupInputField = getField(loginView, "groupInputField");
            ipAdressInputField = getField(loginView, "ipAdressInputField");
            portInputField = getField(loginView, "portInputField");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
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
    public void testLoginFunctionality() throws InterruptedException {
        // Simulate typing in the username field
        simulateTyping(usernameInputField, "testUser");
        assertEquals("testUser", usernameInputField.getText());

        // Simulate typing in the password field
        simulateTyping(passwordInputField, "testPass");
        assertEquals("testPass", new String(passwordInputField.getPassword()));

        // Simulate typing in the group field
        simulateTyping(groupInputField, "testGroup");
        assertEquals("testGroup", groupInputField.getText());

        // Assert that the LoginViewModel is updated
        LoginState currentState = loginViewModel.getState();
        assertEquals("testUser", currentState.getUsername());
        assertEquals("testPass", currentState.getPassword());
        assertEquals("testGroup", currentState.getGroupname());

    }

    private void simulateTyping(JTextComponent component, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            // Move the caret to the end of the text before typing
            component.setCaretPosition(component.getDocument().getLength());

            KeyEvent keyEvent = new KeyEvent(
                    component, KeyEvent.KEY_TYPED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, c
            );
            component.dispatchEvent(keyEvent);
            // Pause to simulate natural typing speed
            sleep(50);
        }
    }

}
