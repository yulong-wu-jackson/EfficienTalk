package app.help;

import app.help.TransApi;

public class translate_example {
    private static final String APP_ID = "20231116001882325";
    private static final String SECURITY_KEY = "swPsLB6jBrArqiDF56uJ";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "Welcome to the chat room!\n" +
                "Client 127.0.0.1 said: 2222 : 你好\n" +
                "Client 127.0.0.1 said: 2222 : 周一\n" +
                "Client 127.0.0.1 said: 2222 : 谢谢";
        System.out.println(query);
        String translated = api.getTransResult(query, "zh", "en");
        String key = "\"dst\":\"";

        int startIndex = 0;
        String result = "";
        while ((startIndex = translated.indexOf(key, startIndex)) != -1) {
            // Move the startIndex to the beginning of the actual content
            startIndex += key.length();

            // Find the end index of the content
            int endIndex = translated.indexOf("\"", startIndex);

            // Extract the substring
            result += translated.substring(startIndex, endIndex) + "\n";

            // Move past this "dst" instance for the next iteration
            startIndex = endIndex;
        }
        System.out.println(result);
    }
}
