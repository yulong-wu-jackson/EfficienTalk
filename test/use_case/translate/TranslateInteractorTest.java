package use_case.translate;

import app.help.ApiTokens;
import app.help.TransApi;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslatePresenter;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslateInteractorTest {
    private LoggedInViewModel loggedInViewModel;
    private TranslateController controller;
    JScrollPane scrollPane;
    private TextArea textArea;

    @Before
    public void setUp(){
        loggedInViewModel = new LoggedInViewModel();
        controller = createTranslateUseCase(loggedInViewModel);
        textArea = new TextArea();
        textArea.append("你好");
        scrollPane = new JScrollPane(
                textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER

        );

    }

    @Test
    public void SuccessTest() throws IOException {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setGroupMessage(textArea.getText());
        controller.execute(scrollPane, loggedInState);
        //System.out.println(textArea.getText());
        assertEquals("hello\n",loggedInState.getGroupMessageTranslated());
    }

    private TranslateController createTranslateUseCase(LoggedInViewModel loggedInViewModel) {
        TranslateOutputBoundary translatePresenter = new TranslatePresenter(loggedInViewModel);
        LoggedInState loggedInState = loggedInViewModel.getState();
        TranslateInputBoundary translateInteractor = new TranslateInteractor(translatePresenter);
        return new TranslateController(translateInteractor);

    }
}