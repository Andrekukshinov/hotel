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
}
