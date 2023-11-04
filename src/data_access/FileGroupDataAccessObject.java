package data_access;

import entity.Group;
import entity.GroupFactory;
import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileGroupDataAccessObject {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Group> groups = new HashMap<>();
    private GroupFactory groupFactory;

    public FileGroupDataAccessObject(String csvPath, GroupFactory groupFactory) throws IOException {
        this.groupFactory = groupFactory;
        csvFile = new File(csvPath);
        headers.put("groupname", 0);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("groupname");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String groupname = String.valueOf(col[headers.get("groupname")]);

                    Group group = groupFactory.create(groupname);
                    groups.put(groupname, group);
                }
            }
        }
    }

    public void save(Group group) {
        groups.put(group.getGroupName(), group);
        this.save();
    }

    public Group get(String groupname) {
        return groups.get(groupname);
    }

    public boolean existsByName(String identifier) {
        return groups.containsKey(identifier);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Group group : groups.values()) {
                String line = String.format("%s",
                        group.getGroupName());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearGroups() {
        groups.clear();
        this.save();
    }
}
