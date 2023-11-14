package app.help;

import okhttp3.*;

import java.io.IOException;

public class SummaryApi {
    private static final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "YOUR_API_KEY_HERE";

    public static String summarize(String text) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"prompt\": \"" + text + "\",\n  \"max_tokens\": 100\n}");
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        String originalText = "Your text to summarize goes here...";
        String summary = summarize(originalText);
        System.out.println("Summarized Text: " + summary);
    }
}
