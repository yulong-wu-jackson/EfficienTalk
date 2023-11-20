package app;
import app.help.SummaryApi;
public class SummaryExample {
    public static void main(String[] args) {
        try {
            SummaryApi summarizer = new SummaryApi("fc5dfb144aeaeff040a15e320795f1c7");
            String dialogue = "The dialogue to be summarized...";
            String summary = summarizer.getSummary(dialogue);
            System.out.println("Summary: " + summary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}