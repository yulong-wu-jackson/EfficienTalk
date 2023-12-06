package app;

import app.help.TransApi;
import org.junit.Test;
import app.help.translate_example;

public class translateApiTest {
    @Test
    public void test() {
        String APP_ID = "20231116001882325";
        String SECURITY_KEY = "swPsLB6jBrArqiDF56uJ";
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "你好";
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
        assert(result.equals("hello\n"));
    }

    @Test
    public void test2(){
        translate_example.main(null);
    }
}
