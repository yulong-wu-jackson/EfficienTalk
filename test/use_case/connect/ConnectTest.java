package use_case.connect;

import app.ClientApp;
import app.help.SimulateTyping;
import org.junit.After;
import org.junit.Before;

import app.help.SimpleServer;
import org.junit.Test;
import view.ConnectView;
import view.SignupView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.Field;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConnectTest {
    static String ipAddress = "localhost";
    static String port = "8088";

    private JPanel connectView;
    private JTextField ipAdressInputField;
    private JTextField portInputField;
    private JButton connectButton;
    private SimpleServer server;
    Thread thread;

    @Before
    public void setUp(){
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                server = new SimpleServer(8088);
                server.start();
            }
        });
        thread.start();

        try {
            ClientApp.main(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Access private fields using reflection
        connectView = getView();
        try {
            ipAdressInputField = getField(connectView, "ipAdressInputField");
            portInputField = getField(connectView, "portInputField");
            connectButton = getField(connectView, "connectButton");
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

    public JPanel getView() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        ConnectView sv = (ConnectView) jp2.getComponent(3);
        return sv;

    }

    private JButton findJOptionPaneOkButton(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                return (JButton) component;
            } else if (component instanceof Container) {
                JButton button = findJOptionPaneOkButton((Container) component);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    @Test
    public void testConnectFunctionality() throws InterruptedException {

        // Simulate typing in the IP address field
        SimulateTyping.simulateTyping(ipAdressInputField, ipAddress);
        assertEquals(ipAddress, ipAdressInputField.getText());

        // Simulate typing in the port field
        SimulateTyping.simulateTyping(portInputField, port);
        assertEquals(port, portInputField.getText());

        // Assert that the ConnectViewModel is updated
        connectButton.doClick();
        assertEquals(1, server.printWriters.size());// check 1 client connected

    }

    @Test
    public void testConnectFailed() throws InterruptedException {

        // Simulate typing in the IP address field
        SimulateTyping.simulateTyping(ipAdressInputField, "");
        assertEquals("", ipAdressInputField.getText());

        // Simulate typing in the port field
        SimulateTyping.simulateTyping(portInputField, "");
        assertEquals("", portInputField.getText());

        // Assert that the ConnectViewModel is updated
        Thread popupThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        SwingUtilities.invokeLater(() -> {
                            Window[] windows = Window.getWindows();
                            for (Window window : windows) {
                                if (window instanceof JDialog && window.isVisible()) {
                                    JDialog dialog = (JDialog) window;
                                    JButton okButton = findJOptionPaneOkButton(dialog);
                                    if (okButton != null) {
                                        okButton.doClick();
                                        break; // Break after clicking the button
                                    }
                                }
                            }
                        });
                        Thread.sleep(500); // A short delay to avoid high CPU usage
                    }
                } catch (InterruptedException e) {
                    // Thread was interrupted, safely exit the loop
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                }
            }
        });
        popupThread.start();
        connectButton.doClick();
        assertEquals(0, server.printWriters.size());// check 0 client connected
        popupThread.interrupt();
        System.out.println(popupThread.isAlive());

    }

    @After
    public void tearDown() {
        if (server != null) {
            server.stop(); // Assuming your SimpleServer class has a stop() method to shut it down
        }
    }
}
