package use_case.signup;

public class SignupOutputData {

    private final String username;
    private String email;

    private boolean useCaseFailed;

    public SignupOutputData(String username, String email, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
