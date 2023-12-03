package app;
import app.help.SummaryApi;
import org.junit.Test;

public class summaryApiTest {
    @Test
    public void testSummarayApi() {
        try {
            SummaryApi summarizer = new SummaryApi("fc5dfb144aeaeff040a15e320795f1c7");
            String dialogue = "The dialogue to be summarized...";
            String summary = summarizer.getSummary(dialogue);
            assert(summary != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
