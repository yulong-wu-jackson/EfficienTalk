package data_access;

import use_case.save.SaveUserDataAccessInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaveDataAccessObject implements SaveUserDataAccessInterface {
    public FileSaveDataAccessObject(){};
    public static void saveStringToFile(String content, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }
    @Override
    public void saveMessage(String savedMessage) {
        String filePath = "savedMessage.txt";

        try {
            saveStringToFile(savedMessage, filePath);
            System.out.println("Content saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }
}
