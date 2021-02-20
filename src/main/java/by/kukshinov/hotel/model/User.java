package by.kukshinov.hotel.model;


import by.kukshinov.hotel.model.enums.Role;

public class User implements Entity {
    private Long userId;
    private String login;
    private boolean isDisabled;
    private Role role;

    public User(Long userId, String login, boolean isDisabled, Role role) {
        this.userId = userId;
        this.login = login;
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

    public void setId(Long userId) {
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

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", isDisabled=" + isDisabled +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (isDisabled != user.isDisabled) {
            return false;
        }
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) {
            return false;
        }
        return getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (isDisabled ? 1 : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }
}
