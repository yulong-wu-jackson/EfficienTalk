package app.help;

import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

public class OpenAISummarizer implements OpenAISummaryAPI{
    private String apiKey = System.getenv("OPAI_TOKEN");

    @Override
    public String getAiSummary(String text) {
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");

            // Create a connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            // Set the required headers
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Authorization", "Bearer " + this.apiKey);
            con.setDoOutput(true);

            // Create the JSON payload
            JSONArray messages = new JSONArray();
            messages.put(new JSONObject().put("role", "system")
                    .put("content",
                            "You are a helpful assistant who helps summarize dialogues.Word limit under 100 words"));
            messages.put(new JSONObject().put("role", "user").put("content", text));

            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("messages", messages);

            // Send the request
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                String output = response.toString();
                JSONObject obj = new JSONObject(output);
                JSONArray choices = obj.getJSONArray("choices");
                if (choices != null && !choices.isEmpty()) {
                    JSONObject firstChoice = choices.getJSONObject(0);
                    JSONObject message = firstChoice.getJSONObject("message");
                    String content = message.getString("content");
                    StringBuilder formattedMessage = new StringBuilder();
                    int counter = 0;

                    for (char ch : content.toCharArray()) {
                        if ((counter >= 110 && ch == ' ') || counter >= 110 + 10) {
                            formattedMessage.append('\n');
                            counter = 0;
                        } else {
                            formattedMessage.append(ch);
                            counter++;
                        }
                    }
                    return formattedMessage.toString();

                }
                return "Content not found";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}