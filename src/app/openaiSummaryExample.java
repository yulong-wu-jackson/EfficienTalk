package app;
import app.help.OpenAISummarizer;
public class openaiSummaryExample {
    public static void main(String[] args) {
        try {
            OpenAISummarizer summarizer = new OpenAISummarizer("YOUR_API_KEY");
            String text = "The text you want to summarize...";
            String summary = summarizer.getSummary(text);
            System.out.println("Summary: " + summary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}