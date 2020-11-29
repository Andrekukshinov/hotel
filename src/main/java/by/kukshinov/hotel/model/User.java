package by.kukshinov.hotel.model;

public class User {
    private long userId;
    private String login;
    private String password;
    private boolean isDisabled;

    public User(long userId, String login, String password, boolean isDisabled) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.isDisabled = isDisabled;
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean getIsDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isDisabled=" + isDisabled +
                '}';
    }

    @Override
    public boolean equals(Object thatUser) {
        if (this == thatUser) {
            return true;
        }
        if (thatUser == null || getClass() != thatUser.getClass()) {
            return false;
        }

        User user = (User) thatUser;

        if (getUserId() != user.getUserId()) {
            return false;
        }
        if (isDisabled != user.isDisabled) {
            return false;
        }
        String thatUserLogin = user.getLogin();
        if (getLogin() != null ? !getLogin().equals(thatUserLogin) : thatUserLogin != null) {
            return false;
        }
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isDisabled ? 1 : 0);
        return result;
    }

}
