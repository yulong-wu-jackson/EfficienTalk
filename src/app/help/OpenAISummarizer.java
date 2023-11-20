package app.help;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class OpenAISummarizer {
    private String apiKey;

    public OpenAISummarizer(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSummary(String text) throws Exception {
        String prompt = "Summarize this: " + text;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/engines/davinci/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(
                        new JSONObject()
                                .put("prompt", prompt)
                                .put("max_tokens", 100) // Adjust max tokens as needed
                                .toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text");
    }
}