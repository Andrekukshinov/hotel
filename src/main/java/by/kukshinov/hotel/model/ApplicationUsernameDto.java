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

    @Override
    public String toString() {
        return "ApplicationUsernameDto{" +
                "application=" + application +
                ", login='" + login + '\'' +
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

        ApplicationUsernameDto that = (ApplicationUsernameDto) o;

        if (getApplication() != null ? !getApplication().equals(that.getApplication()) : that.getApplication() != null) {
            return false;
        }
        return getLogin() != null ? getLogin().equals(that.getLogin()) : that.getLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getApplication() != null ? getApplication().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        return result;
    }
}
