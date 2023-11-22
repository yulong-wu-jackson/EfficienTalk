package interface_adapter.translate;

import interface_adapter.logged_in.LoggedInState;
import use_case.translate.TranslateInputBoundary;
import use_case.translate.TranslateInputData;
import javax.swing.*;

public class TranslateController {
    final TranslateInputBoundary userTranslateUseCaseInteractor;

    public TranslateController(TranslateInputBoundary userTranslateUseCaseInteractor) {
        this.userTranslateUseCaseInteractor = userTranslateUseCaseInteractor;
    }

    public void execute(JScrollPane scrollPane, LoggedInState loggedInState) {
        TranslateInputData translateInputData = new TranslateInputData(scrollPane, loggedInState);
        userTranslateUseCaseInteractor.execute(translateInputData);
    }
}
