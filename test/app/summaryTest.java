package app;
import app.help.SummaryApi;
import data_access.FileSaveDataAccessObject;
import interface_adapter.save.SaveController;
import interface_adapter.save.SavePresenter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.save.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class summaryTest {
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
