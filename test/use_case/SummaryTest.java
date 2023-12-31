package use_case;
import app.help.OpenAISummarizer;
import app.help.OpenAISummaryAPI;
import data_access.FileSummayDataAccessObject;
import interface_adapter.summary.SummaryPresenter;
import interface_adapter.summary.SummaryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.summary.SummaryInputData;
import use_case.summary.SummaryOutputData;
import use_case.summary.SummaryUserDataAccessInterface;
import use_case.summary.SummaryInteractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SummaryTest {

    private SummaryInteractor summaryInteractor;
    private SummaryController summaryController;
    private SummaryPresenter summaryPresenter;
    private FileSummayDataAccessObject fileSummaryDataAccessObject;
    private SummaryUserDataAccessInterface userDataAccessInterface;
    private SummaryPresenter outputBoundary;
    private OpenAISummaryAPI openAISummaryAPI;
    private OpenAISummarizer openAISummarizer;

    @BeforeEach
    public void setUp() {
        fileSummaryDataAccessObject = new FileSummayDataAccessObject();
        summaryPresenter = new SummaryPresenter();
        openAISummarizer = new OpenAISummarizer();
        openAISummaryAPI = openAISummarizer;
        outputBoundary = summaryPresenter; // Assigning SavePresenter instance to SaveOutputBoundary
        userDataAccessInterface = fileSummaryDataAccessObject;
        summaryInteractor = new SummaryInteractor(userDataAccessInterface, outputBoundary, openAISummaryAPI);
        summaryController = new SummaryController(summaryInteractor);
    }

    @Test
    public void testGetInteractor() {
        SummaryInputData summaryInputData = new SummaryInputData("Welcome to the chat room!\nTest");
        summaryInteractor.execute(summaryInputData);
        SummaryOutputData summaryoutputdata = new SummaryOutputData(new String("1"));
        assert(summaryoutputdata != null);
        assert(summaryoutputdata.getSummary() != null);
    }

    @Test
    public void testFailInteractor() {
        SummaryInputData summaryInputData = new SummaryInputData("Welcome to the chat room!\n");
        summaryInteractor.execute(summaryInputData);
        SummaryOutputData summaryoutputdata = new SummaryOutputData(new String("1"));
        assert(summaryoutputdata != null);
        assert(summaryoutputdata.getSummary() != null);
    }

    @Test
    public void testGetController() {
        summaryController.execute("Welcome to the chat room!\nTest");
        SummaryOutputData summaryoutputdata = new SummaryOutputData(new String("1"));
        assert(summaryoutputdata != null);
        assert(summaryoutputdata.getSummary() != null);
    }

    @Test
    public void testSaveInteractor() {
        SummaryInputData inputData = new SummaryInputData("Welcome to the chat room!\nTestTestTest");
        summaryInteractor.execute(inputData);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String filePath = "summary_" + timestamp + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = reader.readLine();
            assert(line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(filePath);
        file.delete();
    }

    @Test
    public void testSaveController() {
        summaryController.execute("Welcome to the chat room!\nTest");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String filePath = "summary_" + timestamp + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = reader.readLine();
            assert(line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(filePath);
        file.delete();
    }
}
