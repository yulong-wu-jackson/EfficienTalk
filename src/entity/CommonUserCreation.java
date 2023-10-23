package entity;

public class CommonUserCreation implements UserCreation{
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @param email
     * @return
     */

    @Override
    public User create(String name, String password, String email) {
        return new CommonUser(name, password, email);
    }
}
