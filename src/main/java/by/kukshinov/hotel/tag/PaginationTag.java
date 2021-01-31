package by.kukshinov.hotel.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport implements Tag {

    private static final String OPENING_DIV = "<div class=\"pages\">";
    private static final String CLOSING_DIV = "</div>";
    private static final String HREF_1ST_PART = "<a href=\"";
    private static final String DIV_CURRENT_PAGE = "<div class=\"pagination-children current-page\">";
    private static final String NEXT = "❯";
    private static final String PREVIOUS = "❮";
    private static final String CLOSING_HREF = "</a>\n";
    private static final String HREF_2ND_PART = "\" class=\"pagination-children\">";
    private static final int FIRST_PAGE = 1;
    private static final String DIV_PAGINATION_CHILDREN = "<div class=\"pagination-children\">";
    private static final String PAGES_SKIP = "...";
    private static final int SECOND_PAGE = 2;
    private static final int THIRD_PAGE = 3;

    private String href;
    private int currentPage;
    private int lastPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            if (lastPage != 0) {
                writer.write(OPENING_DIV);
                previousPage(writer);
                currentPage(writer);
                nextPage(writer);
                writer.write(CLOSING_DIV);
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return SKIP_BODY;
    }

    private void nextPage(JspWriter writer) throws IOException {
        if (currentPage == lastPage) {
            writer.write(HREF_1ST_PART + HREF_2ND_PART + NEXT + CLOSING_HREF);
        } else {
            writer.write(HREF_1ST_PART + href + (currentPage + 1) + HREF_2ND_PART + NEXT + CLOSING_HREF);
        }
    }

    private void currentPage(JspWriter writer) throws IOException {
        if (lastPage <= THIRD_PAGE) {
            displayAllPages(writer);
        } else {
            multiplePages(writer);
        }
    }

    private void previousPage(JspWriter writer) throws IOException {
        if (currentPage - 1 == 0) {
            writer.write(HREF_1ST_PART + HREF_2ND_PART + PREVIOUS + CLOSING_HREF);
        } else {
            writer.write(HREF_1ST_PART + href + (currentPage - 1) + HREF_2ND_PART + PREVIOUS + CLOSING_HREF);
        }
    }

    private void multiplePages(JspWriter writer) throws IOException {
        printFirstPage(writer);
        if (currentPage - 1 == FIRST_PAGE) {
            writer.write(DIV_CURRENT_PAGE + currentPage + CLOSING_DIV);
            writer.write(DIV_PAGINATION_CHILDREN + PAGES_SKIP + CLOSING_DIV);
        } else if (currentPage + 1 == lastPage) {
            writer.write(DIV_PAGINATION_CHILDREN + PAGES_SKIP + CLOSING_DIV);
            writer.write(DIV_CURRENT_PAGE + currentPage + CLOSING_DIV);
        } else if (currentPage == lastPage || currentPage == FIRST_PAGE) {
            writer.write(DIV_PAGINATION_CHILDREN + PAGES_SKIP + CLOSING_DIV);
        } else {
            writer.write(DIV_PAGINATION_CHILDREN + PAGES_SKIP + CLOSING_DIV);
            writer.write(DIV_CURRENT_PAGE + currentPage + CLOSING_DIV);
            writer.write(DIV_PAGINATION_CHILDREN + PAGES_SKIP + CLOSING_DIV);
        }
        printLastPage(writer);
    }

    private void printLastPage(JspWriter writer) throws IOException {
        if (currentPage == lastPage) {
            writer.write(DIV_CURRENT_PAGE + currentPage + CLOSING_DIV);
        } else {
            writer.write(HREF_1ST_PART + href + lastPage + HREF_2ND_PART + lastPage + CLOSING_HREF);
        }
    }

    private void displayAllPages(JspWriter writer) throws IOException {
        for (int runner = 0; runner < lastPage; ++runner) {
            int page = runner + 1;
            if (page == currentPage) {
                writer.write(DIV_CURRENT_PAGE + currentPage + CLOSING_DIV);
            } else {
                writer.write(HREF_1ST_PART + (href + page) + HREF_2ND_PART + page + CLOSING_HREF);
            }
        }
    }

    private void printFirstPage(JspWriter writer) throws IOException {
        if (currentPage == FIRST_PAGE) {
            writer.write(DIV_CURRENT_PAGE + currentPage + CLOSING_DIV);
        } else {
            writer.write(HREF_1ST_PART + (href + FIRST_PAGE) + HREF_2ND_PART + FIRST_PAGE + CLOSING_HREF);
        }
    }

}
