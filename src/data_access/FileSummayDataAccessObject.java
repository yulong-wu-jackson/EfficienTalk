package data_access;

import use_case.summary.SummaryUserDataAccessInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String formattedMessage = formatMessageWithLineBreaks(summary, 110);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String filePath = "summary_" + timestamp + ".txt";

        try {
            saveStringToFile(formattedMessage, filePath);
            System.out.println("Content saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }

    private String formatMessageWithLineBreaks(String message, int lineLength) {
        StringBuilder formattedMessage = new StringBuilder();
        int counter = 0;

        for (char ch : message.toCharArray()) {
            if ((counter >= lineLength && ch == ' ') || counter >= lineLength + 10) {
                formattedMessage.append('\n');
                counter = 0;
            } else {
                formattedMessage.append(ch);
                counter++;
            }
        }

        return formattedMessage.toString();
    }
}
