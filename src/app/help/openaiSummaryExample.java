package app.help;
import app.help.OpenAISummaryAPI;
public class openaiSummaryExample {
    public static void main(String[] args) {
        try {
            OpenAISummaryAPI client = new OpenAISummaryAPI("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");
            String summary = client.getSummary("Hello");
            System.out.println(summary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}