package data_access;

import use_case.save.SaveUserDataAccessInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        String formattedMessage = formatMessageWithLineBreaks(savedMessage, 110);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String filePath = "savedMessage_" + timestamp + ".txt";

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
            if (counter >= lineLength && ch == ' ') {
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
