package User;

public class User {

    private final String USER_EMAIL ;
    private final String USERNAME ;
    private final String PASSWORD ;

    private User(UserBuilder builder) {
        this.USER_EMAIL = builder.USER_EMAIL;
        this.USERNAME = builder.USERNAME;
        this.PASSWORD = builder.PASSWORD;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public String getUserEmail() {
        return USER_EMAIL;
    }

    public String getUsername() {
        return USERNAME;
    }

    public static class UserBuilder
    {
        private final String USER_EMAIL ;
        private String USERNAME ;
        private String PASSWORD;

        public UserBuilder(String USER_EMAIL) {
            this.USER_EMAIL = USER_EMAIL;
        }
        public UserBuilder withUserName(String USERNAME) {
            this.USERNAME = USERNAME;
            return this;
        }
        public UserBuilder withPassword(String PASSWORD) {
            this.PASSWORD = PASSWORD;
            return this;
        }
        public User build() {
            User user = new User(this);
            return user;
        }
    }
}
