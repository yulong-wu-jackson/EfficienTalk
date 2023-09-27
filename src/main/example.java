package main;

import main.help.TransApi;

public class example {
    private static final String APP_ID = "20230927001831049";
    private static final String SECURITY_KEY = "TpImnqfP5aSnRmCnsAL5";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "我在做软件设计课的作业";
        System.out.println(api.getTransResult(query, "auto", "en"));
    }
}
