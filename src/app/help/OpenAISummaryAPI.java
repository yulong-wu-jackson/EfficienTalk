package app.help;

public interface OpenAISummaryAPI {
    String getAiSummary(String text, String apiKey);

    String getApikey();
}
