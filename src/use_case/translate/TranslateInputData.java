package use_case.translate;

import interface_adapter.logged_in.LoggedInState;

import javax.swing.*;

public class TranslateInputData {
    final private JScrollPane scrollPane;
    final private LoggedInState loggedInState;

    public TranslateInputData(JScrollPane scrollPane, LoggedInState loggedInState) {
        this.scrollPane = scrollPane;
        this.loggedInState = loggedInState;
    }

    public JScrollPane getScrollPane() {return scrollPane;}

    public LoggedInState getLoggedInState() {return loggedInState;}

}
