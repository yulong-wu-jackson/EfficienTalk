package interface_adapter.translate;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.translate.TranslateOutputBoundary;
import use_case.translate.TranslateOutputData;

import javax.swing.*;
import java.awt.*;

public class TranslatePresenter implements TranslateOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;

    public TranslatePresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(TranslateOutputData translateOutputData) {
        JScrollPane scrollpane = translateOutputData.getScrollPane();
        TextArea translatedTextArea = translateOutputData.getTranslatedTextArea();
        scrollpane.setViewportView(translatedTextArea);
    }
}
