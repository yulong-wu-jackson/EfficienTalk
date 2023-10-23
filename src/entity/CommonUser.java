package entity;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final String email;


    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @param email
     */
    CommonUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}

