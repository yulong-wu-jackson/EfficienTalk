package app.help;

public class ApiTokens {

    private final String APP_ID = "20231116001882325";
    private final String SECURITY_KEY = "swPsLB6jBrArqiDF56uJ";
    private final String OPAI_TOKEN = System.getenv("OPAI_TOKEN");

    public String getAPP_ID() {return APP_ID;}

    public String getSECURITY_KEY() {return SECURITY_KEY;}
}
