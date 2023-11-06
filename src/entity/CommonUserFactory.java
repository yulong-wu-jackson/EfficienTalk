package entity;

public class CommonUserFactory implements UserFactory {
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
