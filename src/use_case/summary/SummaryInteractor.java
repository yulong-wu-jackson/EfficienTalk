package use_case.summary;
import app.help.OpenAISummaryAPI;
public class SummaryInteractor implements SummaryInputBoundary{
    final SummaryUserDataAccessInterface userDataAccessObject;
    final SummaryOutputBoundary userPresenter;
    private final OpenAISummaryAPI openAISummaryAPI;

    public SummaryInteractor(SummaryUserDataAccessInterface summaryUserDataAccessInterface,
                             SummaryOutputBoundary summaryOutputBoundary,
                             OpenAISummaryAPI openAISummaryAPI) {
        this.userDataAccessObject = summaryUserDataAccessInterface;
        this.userPresenter = summaryOutputBoundary;
        this.openAISummaryAPI = openAISummaryAPI;
    }

    @Override
    public void saveSummary(String summary) {
        userDataAccessObject.saveSummary(summary);
        userPresenter.prepareSuccessView(summary);
    }

    @Override
    public String getSummary(SummaryInputData summaryInputData) {
        String groupMessage = summaryInputData.getGroupMessage();
        StringBuilder builder = new StringBuilder(groupMessage);
        builder.delete(0,26);
        String modifiedGroupMessage = builder.toString();
        String summary = openAISummaryAPI.getAiSummary(modifiedGroupMessage);
        return("Summary: " + summary);
    }
}
