package app.help;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class SummaryApi {

        private String apiKey;

        public SummaryApi(String apiKey) {
                this.apiKey = apiKey;
        }

        public String getSummary(String text) throws Exception {
                String requestParams = "key=" + apiKey + "&txt=" + URLEncoder.encode(text, StandardCharsets.UTF_8)
                        + "&sentences=5"; // Number of sentences in the summary

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.meaningcloud.com/summarization-1.0"))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(HttpRequest.BodyPublishers.ofString(requestParams))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                JSONObject jsonResponse = new JSONObject(response.body());
                return jsonResponse.optString("summary", "No summary available.");
        }
}