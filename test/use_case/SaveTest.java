package use_case;
import data_access.FileSaveDataAccessObject;
import interface_adapter.save.SaveController;
import interface_adapter.save.SavePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.save.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveTest {

    private SaveInteractor saveInteractor;
    private SaveController saveController;
    private SavePresenter savePresenter;
    private FileSaveDataAccessObject fileSaveDataAccessObject;
    private SaveUserDataAccessInterface userDataAccessInterface;
    private SavePresenter outputBoundary;
    private SaveOutputData saveOutputData;

    @BeforeEach
    public void setUp() {
        // Initialize your classes here with actual dependencies
        fileSaveDataAccessObject = new FileSaveDataAccessObject();
        savePresenter = new SavePresenter();
        outputBoundary = savePresenter; // Assigning SavePresenter instance to SaveOutputBoundary

        userDataAccessInterface = fileSaveDataAccessObject;
        saveInteractor = new SaveInteractor(userDataAccessInterface, outputBoundary);
        saveController = new SaveController(saveInteractor);
    }

    @Test
    public void testGetInteractor() throws Exception {
        SaveInputData inputData = new SaveInputData("Welcome to the chat room!\nTest");
        String outputData = saveInteractor.getMessage(inputData);
        SaveOutputData saveoutputdata = new SaveOutputData(outputData);
        assert(outputData.equals("Test"));
        assert(saveoutputdata.getMessage().equals("Test"));
    }

    @Test
    public void testGetController() throws Exception {
        String message = saveController.getMessage("Welcome to the chat room!\nTest");
        SaveOutputData saveoutputdata = new SaveOutputData(message);
        assert(message.equals("Test"));
        assert(saveoutputdata.getMessage().equals("Test"));
    }

    @Test
    public void testSaveInteractor() {
        SaveInputData inputData = new SaveInputData("Welcome to the chat room!\nTest");
        String outputData = saveInteractor.getMessage(inputData);
        saveInteractor.saveMessage(outputData);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String filePath = "savedMessage_" + timestamp + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = reader.readLine();
            assert(line.equals("Test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(filePath);
        file.delete();
    }

    @Test
    public void testSaveController() {
        String message = saveController.getMessage("Welcome to the chat room!\nTest");
        saveController.saveMessage(message);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String filePath = "savedMessage_" + timestamp + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            line = reader.readLine();
            assert(line.equals("Test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(filePath);
        file.delete();
    }
}
