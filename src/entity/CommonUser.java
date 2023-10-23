package entity;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final String email;
    private final String status;


    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, String email, String status) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = status;
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
    public String getStatus(){return status;}



}

