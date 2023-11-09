package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.send_message.SendMessageController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.Socket;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final LogoutController logoutController;
    private final SendMessageController sendMessageController;

    JLabel username;
    JLabel address;
    JLabel port;
    final JTextField messageInputField = new JTextField(30);
    final JButton send;
    final JButton notify;

    final JButton logOut;
    public static JTextArea textArea;
    static Socket socket = null;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, LogoutController logoutController,
                        SendMessageController sendMessageController) {
        this.loggedInViewModel = loggedInViewModel;
        this.logoutController = logoutController;
        this.sendMessageController = sendMessageController;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        address = new JLabel();
        port = new JLabel();

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font(null, Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(
                textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER

        );

        LabelTextPanel clientMessage = new LabelTextPanel(
                new JLabel("Your Message"), messageInputField);

        JPanel buttons = new JPanel();
        send = new JButton(loggedInViewModel.SEND_BUTTON_LABEL);
        buttons.add(send);
        notify = new JButton(loggedInViewModel.NOTIFICATION_BUTTON_LABEL);
        buttons.add(notify);
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);


        textArea.append("Welcome to the chat room!\n");


        messageInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoggedInState currentState = loggedInViewModel.getState();
                currentState.setClientMessage(messageInputField.getText() + e.getKeyChar());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        send.setEnabled(false);


        send.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(send)) {
                            LoggedInState currentState = loggedInViewModel.getState();
                            // TODO: send message to group
                            sendMessageController.execute(currentState.getUsername() + " : " +
                                    currentState.getClientMessage(), currentState.getSocket());

                            // TODO: after client sent message, clear the message input field
                            messageInputField.setText("");
                            currentState = loggedInViewModel.getState();
                            currentState.setClientMessage(messageInputField.getText());
                            loggedInViewModel.setState(currentState);
                        }
                    }
                }
        );

        notify.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(notify)) {
                            // TODO: send notification to group
                            // notifyController.execute();

                            // after client sent notification, clear the message input field
                            messageInputField.setText("");
                            LoggedInState currentState = loggedInViewModel.getState();
                            currentState.setClientMessage(messageInputField.getText());
                            loggedInViewModel.setState(currentState);
                        }
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logOut)) {
                            logoutController.execute();
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(address);
        this.add(port);
        this.add(scrollPane);
        this.add(clientMessage);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        address.setText(state.getIpAddress());
        port.setText(state.getPort());
        if (state.getSocket() != null) {
            send.setEnabled(true);
        }

    }
}