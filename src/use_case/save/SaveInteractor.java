package use_case.save;

import app.help.OpenAISummaryAPI;
import use_case.summary.SummaryInputData;
import use_case.summary.SummaryOutputBoundary;
import use_case.summary.SummaryUserDataAccessInterface;

public class SaveInteractor implements SaveInputBoundary{
    final SaveUserDataAccessInterface userDataAccessObject;
    final SaveOutputBoundary userPresenter;

    public SaveInteractor(SaveUserDataAccessInterface saveUserDataAccessInterface,
                             SaveOutputBoundary saveOutputBoundary) {
        this.userDataAccessObject = saveUserDataAccessInterface;
        this.userPresenter = saveOutputBoundary;
    }

    @Override
    public void saveMessage(String summary) {
        userDataAccessObject.saveMessage(summary);
        userPresenter.prepareSuccessView(summary);
    }

    @Override
    public String getMessage(SaveInputData saveInputData) {
        String groupMessage = saveInputData.getGroupMessage();
        StringBuilder builder = new StringBuilder(groupMessage);
        builder.delete(0,26);
        String modifiedGroupMessage = builder.toString();
        return(modifiedGroupMessage);
    }
}