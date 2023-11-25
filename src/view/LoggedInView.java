package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.save.SaveController;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.summary.SummaryController;
import interface_adapter.notify.NotifyController;
import interface_adapter.notify.NotifyState;
import interface_adapter.notify.NotifyViewModel;
import interface_adapter.translate.TranslateController;


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

    private final NotifyViewModel notifyViewModel;
    private final LogoutController logoutController;
    private final SendMessageController sendMessageController;

    private final SummaryController summaryController;

    private final SaveController saveController;

    private final TranslateController translateController;

    private final NotifyController notifyController;

    JLabel username;
    JLabel address;
    JLabel port;
    final JTextField messageInputField = new JTextField(30);
    final JButton send;
    final JButton notify;
    final JButton logOut;
    final JButton summary;
    final JButton save;

    final JCheckBox translate;

    public static JTextArea textArea;
    static Socket socket = null;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, NotifyViewModel notifyViewModel, LogoutController logoutController,
                        SendMessageController sendMessageController, 
                        TranslateController translateController, NotifyController notifyController, 
                        SummaryController summaryController, SaveController saveController) {

        this.loggedInViewModel = loggedInViewModel;
        this.logoutController = logoutController;
        this.sendMessageController = sendMessageController;
        this.notifyController = notifyController;
        this.summaryController = summaryController;
        this.saveController = saveController;

        this.loggedInViewModel.addPropertyChangeListener(this);
        LoggedInView self = this;

        this.notifyViewModel = notifyViewModel;
        this.notifyViewModel.addPropertyChangeListener(this);

        this.translateController = translateController;


        JLabel title = new JLabel("Chat Room");
        title.setFont(new Font(null, Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        address = new JLabel();
        port = new JLabel();

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font(null, Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(
                textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER

        );
        scrollPane.setPreferredSize(new Dimension(500, 300));

        LabelTextPanel clientMessage = new LabelTextPanel(
                new JLabel("Your Message"), messageInputField);

        JPanel buttons = new JPanel();
        send = new JButton(loggedInViewModel.SEND_BUTTON_LABEL);
        buttons.add(send);
        notify = new JButton(loggedInViewModel.NOTIFICATION_BUTTON_LABEL);
        buttons.add(notify);
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        summary = new JButton(loggedInViewModel.SUMMARY_BUTTON_LABEL);
        buttons.add(summary);
        save = new JButton(loggedInViewModel.SAVE_BUTTON_LABEL);
        buttons.add(save);

        translate = new JCheckBox(loggedInViewModel.TRANSLATE_CHECK_BOX_LABEL);



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
                            LoggedInState currentState = loggedInViewModel.getState();
                            notifyController.execute(currentState.getClientMessage());

                            // TODO: send notification to group
                            // notifyController.execute();
                            // after client sent notification, clear the message input field
                            messageInputField.setText("");
                            currentState = loggedInViewModel.getState();
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


        summary.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(summary)) {
                            String groupMessage = textArea.getText();
                            String summary = summaryController.getSummary(groupMessage);
                            summaryController.saveSummary(summary);
                            JOptionPane.showMessageDialog(self, summary);
                        }
                    }
                }
        );

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save)) {
                            String groupMessage = textArea.getText();
                            String savedMessage = saveController.getMessage(groupMessage);
                            saveController.saveMessage(savedMessage);
                            JOptionPane.showMessageDialog(self, "Dialogues have been saved!");
                        }
                    }
                }
        );
      
        translate.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (translate.isSelected()) {
                            LoggedInState loggedInState = loggedInViewModel.getState();
                            loggedInState.setGroupMessage(textArea.getText());
                            translateController.execute(scrollPane, loggedInState);
                        } else {
                            scrollPane.setViewportView(textArea);
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(translate);
//        this.add(address);
//        this.add(port);
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

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//        LoggedInState state = (LoggedInState) evt.getNewValue();
//        username.setText("User Name: " + state.getUsername());
//        address.setText("IP: " + state.getIpAddress());
//        port.setText("port: " + state.getPort());
//        if (state.getSocket() == null) {
//            send.setEnabled(false);
//            notify.setEnabled(false);
//            textArea.append("Cannot connect to server.\n");
//            textArea.append("Please restart the application.\n");
//        }
//        state.setGroupMessage(textArea.getText());
//
//
//
//}

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Object newValue = evt.getNewValue();
        if (newValue instanceof LoggedInState) {
            LoggedInState state = (LoggedInState) newValue;
            username.setText("User Name: " + state.getUsername());
            address.setText("IP: " + state.getIpAddress());
            port.setText("port: " + state.getPort());
            if (state.getSocket() == null) {
            send.setEnabled(false);
            notify.setEnabled(false);
            textArea.append("Cannot connect to server.\n");
            textArea.append("Please restart the application.\n");
        }
        state.setGroupMessage(textArea.getText());
        loggedInViewModel.setState(state);
    }
        else if(newValue instanceof NotifyState) {
            JOptionPane.showMessageDialog(this, "Sent email successfully");
        }
        }
}
