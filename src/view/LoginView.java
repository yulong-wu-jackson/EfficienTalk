package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.switchtosignup.SwitchToSignUpController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();
    final JTextField groupInputField = new JTextField(15);
    final JTextField ipAdressInputField = new JTextField(15);
    final JTextField portInputField = new JTextField(5);

    final JButton logIn;
    final JButton cancel;
    final JButton switchToSignUp;
    private final LoginController loginController;
    private final SwitchToSignUpController switchToSignUpController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, SwitchToSignUpController switchToSignUpController) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.switchToSignUpController = switchToSignUpController;

        JLabel title = new JLabel("Login Screen");
        title.setFont(new Font(null, Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);
        // TODO: add LabelTextPanel GroupInfo(WORKING ON IT)
        LabelTextPanel groupInfo = new LabelTextPanel(
                new JLabel("Group"), groupInputField);
        LabelTextPanel ipInfo = new LabelTextPanel(
                new JLabel("IP Address"), ipAdressInputField);
        LabelTextPanel portInfo = new LabelTextPanel(
                new JLabel("Port"), portInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        logIn.setFont(new Font(null, Font.BOLD, 15));
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        cancel.setFont(new Font(null, Font.BOLD, 15));
        buttons.add(cancel);
        // TODO: add SwitchToSignUp button (DONE)
        switchToSignUp = new JButton(loginViewModel.SWITCH_SIGNUP_BUTTON_LABEL);
        switchToSignUp.setFont(new Font(null, Font.BOLD, 15));
        buttons.add(switchToSignUp);
        // TODO: add ClearGroup button

        // Remember to delete ClearGroup button in client version


        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getGroupname(),
                                    currentState.getIpAddress(),
                                    currentState.getPort()
                            );

                            // clear the input fields
                            usernameInputField.setText("");
                            passwordInputField.setText("");
                            groupInputField.setText("");
                            ipAdressInputField.setText("");
                            portInputField.setText("");
                            currentState.setUsername(usernameInputField.getText());
                            currentState.setPassword(passwordInputField.getText());
                            currentState.setGroupname(groupInputField.getText());
                            currentState.setIpAddress(ipAdressInputField.getText());
                            currentState.setPort(portInputField.getText());
                            loginViewModel.setState(currentState);
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        switchToSignUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(switchToSignUp)) {
                            switchToSignUpController.execute();

                            // clear the input fields
                            LoginState currentState = loginViewModel.getState();
                            usernameInputField.setText("");
                            passwordInputField.setText("");
                            groupInputField.setText("");
                            ipAdressInputField.setText("");
                            portInputField.setText("");
                            currentState.setUsername(usernameInputField.getText());
                            currentState.setPassword(passwordInputField.getText());
                            currentState.setGroupname(groupInputField.getText());
                            currentState.setIpAddress(ipAdressInputField.getText());
                            currentState.setPort(portInputField.getText());
                            loginViewModel.setState(currentState);
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        groupInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setGroupname(groupInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        ipAdressInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setIpAddress(ipAdressInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        portInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setPort(portInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);

        this.add(groupInfo);
        this.add(ipInfo);
        this.add(portInfo);
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
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}