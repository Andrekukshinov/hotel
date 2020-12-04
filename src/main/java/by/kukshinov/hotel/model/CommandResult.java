package by.kukshinov.hotel.model;

public class CommandResult {
    private final String pageUrl;
    private final boolean isRedirect;

    private CommandResult(String pageUrl, boolean isRedirect) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String pageUrl) {
        return new CommandResult(pageUrl, false);
    }

    public static CommandResult redirect(String pageUrl) {
        return new CommandResult(pageUrl, true);
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    @Override
    public String toString() {
        return "CommandResult{" +
                "pageUrl='" + pageUrl + '\'' +
                ", isRedirect=" + isRedirect +
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

        CommandResult that = (CommandResult) o;

        if (isRedirect() != that.isRedirect()) {
            return false;
        }
        return getPageUrl() != null ? getPageUrl().equals(that.getPageUrl()) : that.getPageUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getPageUrl() != null ? getPageUrl().hashCode() : 0;
        result = 31 * result + (isRedirect() ? 1 : 0);
        return result;
    }
}
