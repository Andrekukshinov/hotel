package by.kukshinov.hotel.model;


import by.kukshinov.hotel.model.enums.Role;

public class User implements Entity {
    private Long userId;
    private String login;
    private String password;
    private boolean isDisabled;
    private Role role;

    public User(Long userId, String login, String password, boolean isDisabled, Role role) {
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
    public Long getId() {
        return userId;
    }
}
