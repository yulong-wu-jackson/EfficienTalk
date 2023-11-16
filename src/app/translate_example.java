package app;

import app.help.TransApi;

public class translate_example {
    private static final String APP_ID = "20231116001882325";
    private static final String SECURITY_KEY = "swPsLB6jBrArqiDF56uJ";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "加拿大多伦多大学就是一坨狗屎。。。";
        String translated = api.getTransResult(query, "auto", "en");
        String[] cut = translated.split(":");
        String[] result = cut[5].split("}");
        System.out.println(result[0]);
    }
}
