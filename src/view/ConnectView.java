package view;

import interface_adapter.connect.ConnectController;
import interface_adapter.connect.ConnectState;
import interface_adapter.connect.ConnectViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ConnectView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "connect";
    private final ConnectViewModel connectViewModel;
    final JTextField ipAdressInputField = new JTextField(15);
    final JTextField portInputField = new JTextField(5);
    final JButton connectButton;
    private final ConnectController connectController;

    public ConnectView(ConnectViewModel connectViewModel, ConnectController connectController) {
        this.connectController = connectController;
        this.connectViewModel = connectViewModel;
        this.connectViewModel.addPropertyChangeListener(this);

        JLabel titleLabel = new JLabel("Connect Screen");
        titleLabel.setAlignmentX(0.5f);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18.0f));

        LabelTextPanel ipInfo = new LabelTextPanel(
                new JLabel("IP Address"), ipAdressInputField);
        LabelTextPanel portInfo = new LabelTextPanel(
                new JLabel("Port"), portInputField);

        connectButton = new JButton(connectViewModel.CONNECT_BUTTON_LABEL);

        JPanel buttons = new JPanel();
        connectButton.setAlignmentX(0.5f);
        connectButton.setFont(new Font(null, Font.BOLD, 15));

        buttons.add(this.connectButton);
        buttons.setAlignmentX(0.5f);

        connectButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(connectButton)) {
                            ConnectState currentState = connectViewModel.getState();
                            connectController.execute(
                                    currentState.getIpAddress(),
                                    currentState.getPort()
                            );

                        }
                    }
                }
        );


        ipAdressInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ConnectState currentState = connectViewModel.getState();
                currentState.setIpAddress(ipAdressInputField.getText() + e.getKeyChar());
                connectViewModel.setState(currentState);
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
                ConnectState currentState = connectViewModel.getState();
                currentState.setPort(portInputField.getText() + e.getKeyChar());
                connectViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        this.setLayout(new BoxLayout(this, 1));
        this.add(titleLabel);
        this.add(ipInfo);
        this.add(portInfo);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ConnectState state = (ConnectState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(ConnectState state) {
        ipAdressInputField.setText(state.getIpAddress());
        portInputField.setText(state.getPort());
    }
}
