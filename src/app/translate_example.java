package app;

import app.help.TransApi;

public class translate_example {
    private static final String APP_ID = "20231116001882325";
    private static final String SECURITY_KEY = "swPsLB6jBrArqiDF56uJ";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "Nice to meet you \n 谢谢你 \n 不客气。\n 下次再见！";
        String translated = api.getTransResult(query, "auto", "en");
        String key = "\"dst\":\"";

        int startIndex = 0;
        while ((startIndex = translated.indexOf(key, startIndex)) != -1) {
            // Move the startIndex to the beginning of the actual content
            startIndex += key.length();

            // Find the end index of the content
            int endIndex = translated.indexOf("\"", startIndex);

            // Extract the substring
            String result = translated.substring(startIndex, endIndex);

            System.out.println(result);

            // Move past this "dst" instance for the next iteration
            startIndex = endIndex;
        }
    }
}
