package by.kukshinov.hotel.model;

public class ApplicationUsernameDto {
    private final Application application;
    private final String login;

    public ApplicationUsernameDto(Application application, String login) {
        this.application = application;
        this.login = login;
    }

    public Application getApplication() {
        return application;
    }

    public String getLogin() {
        return login;
    }
}
