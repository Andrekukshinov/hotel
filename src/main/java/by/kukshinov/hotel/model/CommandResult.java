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
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        CommandResult thatCommandResult = (CommandResult) that;

        if (isRedirect() != thatCommandResult.isRedirect()) {
            return false;
        }
        String thatCommandResultPageUrl = thatCommandResult.getPageUrl();
        return getPageUrl() != null ? getPageUrl().equals(thatCommandResultPageUrl) : thatCommandResultPageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = getPageUrl() != null ? getPageUrl().hashCode() : 0;
        result = 31 * result + (isRedirect() ? 1 : 0);
        return result;
    }
}
