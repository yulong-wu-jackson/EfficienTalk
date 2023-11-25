package data_access;

import entity.User;
import entity.UserFactory;

import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.summary.SummaryUserDataAccessInterface;
import use_case.notify.NotifyUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface,
        ClearUserDataAccessInterface,
        LoginUserDataAccessInterface,
        SummaryUserDataAccessInterface,
        NotifyUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("email", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,email");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    User user = userFactory.create(username, password, email);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }


    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s",
                        user.getName(), user.getPassword(), user.getEmail());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList get_users(){
        ArrayList<String> users = new ArrayList<>(this.accounts.keySet());
        return users;
    }
    public void delete() {

        this.accounts.clear();
        this.save();
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public void clearUsers() {
        accounts.clear();
        this.save();
    }

    @Override
    public ArrayList<String> getUserEmails() {
        ArrayList<String> useremails = new ArrayList<>();
        for (User user : accounts.values()) {
            useremails.add(user.getEmail());
        }
        return useremails;
    }

    public ArrayList<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (User user : accounts.values()) {
            usernames.add(user.getName());
        }
        return usernames;
    }
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