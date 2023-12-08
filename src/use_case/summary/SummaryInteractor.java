package use_case.summary;

import app.help.OpenAISummaryAPI;

/**
 * The {@code SummaryInteractor} class implements the {@code SummaryInputBoundary} interface,
 * orchestrating the flow of data for summarization use cases.
 */
public class SummaryInteractor implements SummaryInputBoundary {
    private final SummaryUserDataAccessInterface userDataAccessObject;
    private final SummaryOutputBoundary userPresenter;
    private final OpenAISummaryAPI openAISummaryAPI;

    /**
     * Constructs a {@code SummaryInteractor} with the specified data access interface,
     * output boundary, and OpenAI API.
     *
     * @param summaryUserDataAccessInterface The data access interface for summary-related data.
     * @param summaryOutputBoundary The output boundary for presenting summary results.
     * @param openAISummaryAPI The OpenAI summary API interface.
     */
    public SummaryInteractor(SummaryUserDataAccessInterface summaryUserDataAccessInterface,
                             SummaryOutputBoundary summaryOutputBoundary,
                             OpenAISummaryAPI openAISummaryAPI) {
        this.userDataAccessObject = summaryUserDataAccessInterface;
        this.userPresenter = summaryOutputBoundary;
        this.openAISummaryAPI = openAISummaryAPI;
    }

    /**
     * Executes the summarization process based on the input group message.
     *
     * @param groupMessage The summary input data containing the group message.
     */
    @Override
    public void execute(SummaryInputData groupMessage) {
        if (groupMessage.getGroupMessage().equals("Welcome to the chat room!\n")) {
            userPresenter.prepareFailView("Error: No messages are input, please do the summarization after chatting!");
        } else {
            String messages = groupMessage.getGroupMessage();
            String apiKey = openAISummaryAPI.getApikey();
            String summary = openAISummaryAPI.getAiSummary(messages, apiKey);
            if (summary.equals("Error: Server returned HTTP response code: 401 for URL: https://api.openai.com/v1/chat/completions")) {
                userPresenter.prepareFailView("There is something wrong with the OpenAi Apikey, please check!");
            } else {
                userDataAccessObject.saveSummary(summary);
                SummaryOutputData summaryOutputData = new SummaryOutputData(summary);
                userPresenter.prepareSuccessView(summaryOutputData);
            }
        }
    }
}
