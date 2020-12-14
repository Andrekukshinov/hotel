package by.kukshinov.hotel.model;


import by.kukshinov.hotel.model.enums.Role;

public class User {
    private long userId;
    private String login;
    private String password;
    private boolean isDisabled;
    private Role role;

    public User(long userId, String login, String password, boolean isDisabled, Role role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.isDisabled = isDisabled;
        this.role = role;
    }

    public User() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public void setIsDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isDisabled=" + isDisabled +
                ", role=" + role +
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
        String thatUserPassword = user.getPassword();
        if (getPassword() != null ? !getPassword().equals(thatUserPassword) : thatUserPassword != null) {
            return false;
        }
        return getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        int result = (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (isDisabled ? 1 : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }
}
