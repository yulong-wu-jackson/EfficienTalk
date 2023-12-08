package app.help;

import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * The {@code OpenAISummarizer} class implements the {@code OpenAISummaryAPI}
 * to provide functionality for summarizing text using the OpenAI API.
 */
public class OpenAISummarizer implements OpenAISummaryAPI {

    private static final int LINE_WRAP_LIMIT = 110;
    private static final int LINE_WRAP_BUFFER = 10;

    /**
     * Retrieves a summary for a given text using the OpenAI API.
     *
     * @param text The text to be summarized.
     * @param apiKey The API key for accessing the OpenAI API.
     * @return The summarized text, or an error message if the operation fails.
     */
    @Override
    public String getAiSummary(String text, String apiKey) {
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");

            // Remove the first 26 characters from the text
            StringBuilder builder = new StringBuilder(text);
            builder.delete(0, 26);
            String modifiedText = builder.toString();

            // Create and configure the HTTP connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setDoOutput(true);

            // Create the JSON payload
            JSONArray messages = new JSONArray();
            messages.put(new JSONObject().put("role", "system")
                    .put("content", "You are a helpful assistant who helps summarize dialogues. Word limit under 100 words"));
            messages.put(new JSONObject().put("role", "user").put("content", modifiedText));

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
                String responseLine;
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
                        if ((counter >= LINE_WRAP_LIMIT && ch == ' ') || counter >= LINE_WRAP_LIMIT + LINE_WRAP_BUFFER) {
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
            } finally {
                con.disconnect();
            }

        } catch (Exception e) {
            // Consider using a logging framework here
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Retrieves the API key for the OpenAI service from the environment variables.
     *
     * @return The API key as a string.
     */
    @Override
    public String getApikey() {
        return System.getenv("OPAI_TOKEN");
    }
}
