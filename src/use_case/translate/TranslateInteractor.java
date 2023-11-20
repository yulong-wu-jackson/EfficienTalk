package use_case.translate;

import app.help.ApiTokens;
import app.help.TransApi;
import interface_adapter.logged_in.LoggedInState;

import javax.swing.*;
import java.awt.*;

public class TranslateInteractor implements TranslateInputBoundary{
    final TranslateOutputBoundary translatePresenter;

    public TranslateInteractor(TranslateOutputBoundary translatePresenter) {
        this.translatePresenter = translatePresenter;
    }

    @Override
    public void execute(TranslateInputData translateInputData) {
        JScrollPane scrollPane = translateInputData.getScrollPane();
        LoggedInState loggedInState = translateInputData.getLoggedInState();
        String message = loggedInState.getGroupMessage();
        ApiTokens apiTokens = new ApiTokens();
        TransApi api = new TransApi(apiTokens.getAPP_ID(), apiTokens.getSECURITY_KEY());
        String translated = api.getTransResult(message, "zh", "en");
        String key = "\"dst\":\"";
        int startIndex = 0;
        String result = "";
        while ((startIndex = translated.indexOf(key, startIndex)) != -1) {
            startIndex += key.length();
            int endIndex = translated.indexOf("\"", startIndex);
            result += translated.substring(startIndex, endIndex) + "\n";
            startIndex = endIndex;
        }
        loggedInState.setGroupMessageTranslated(result);
        JTextArea translatedTextArea = new JTextArea();
        translatedTextArea.setLineWrap(true);
        translatedTextArea.setFont(new Font(null, Font.PLAIN, 16));
        translatedTextArea.append(result);
        TranslateOutputData translateOutputData = new TranslateOutputData(translatedTextArea, scrollPane);
        translatePresenter.prepareSuccessView(translateOutputData);
    }
}
