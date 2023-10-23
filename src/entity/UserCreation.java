package entity;

public interface UserCreation {
    /** Requires: password is valid. */
    User create(String name, String password, String email);
}
