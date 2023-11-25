package data_access;

import use_case.summary.SummaryUserDataAccessInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSummayDataAccessObject implements SummaryUserDataAccessInterface {
    public FileSummayDataAccessObject(){};
    public static void saveStringToFile(String content, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }

    @Override
    public void saveSummary(String summary) {
        String filePath = "summary.txt";

        try {
            saveStringToFile(summary, filePath);
            System.out.println("Content saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while saving the file.");
            e.printStackTrace();
        }

    }
}
