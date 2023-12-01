package view;

import org.junit.Before;
import org.junit.Test;
import interface_adapter.connect.ConnectController;
import interface_adapter.connect.ConnectViewModel;
import interface_adapter.connect.ConnectState;
import use_case.connect.ConnectInputBoundary;
import use_case.connect.ConnectInputData;
import view.ConnectView;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class ConnectViewTest {

    private ConnectViewModel connectViewModel;
    private ConnectView connectView;
    private JTextField ipAdressInputField;
    private JTextField portInputField;
    private JButton connectButton;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        connectViewModel = new ConnectViewModel();
        ConnectInputBoundary connectInputBoundary = new ConnectInputBoundary() {
            @Override
            public void execute(ConnectInputData connectInputData) {
                // Do nothing
            }
        };
        ConnectController connectController = new ConnectController(connectInputBoundary);

        // Initialize ConnectView
        connectView = new ConnectView(connectViewModel, connectController);
        JFrame jf = new JFrame();
        jf.setContentPane(connectView);
        jf.pack();
        jf.setVisible(true);

        // Access private fields using reflection
        ipAdressInputField = getField(connectView, "ipAdressInputField");
        portInputField = getField(connectView, "portInputField");
        connectButton = getField(connectView, "connectButton");
    }

    @SuppressWarnings("unchecked")
    private <T> T getField(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    @Test
    public void testConnectFunctionality() throws InterruptedException {
        // Simulate typing in the IP address field
        simulateTyping(ipAdressInputField, "192.168.1.1");
        assertEquals("192.168.1.1", ipAdressInputField.getText());

        // Simulate typing in the port field
        simulateTyping(portInputField, "8080");
        assertEquals("8080", portInputField.getText());

        // Assert that the ConnectViewModel is updated
        ConnectState currentState = connectViewModel.getState();
        assertEquals("192.168.1.1", currentState.getIpAddress());
        assertEquals("8080", currentState.getPort());
        connectButton.doClick();
        ActionEvent actionEvent = new ActionEvent(connectButton, 0, "action");
        connectView.actionPerformed(actionEvent);
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

