package by.kukshinov.hotel.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatTag extends TagSupport implements Tag {
    private static final DateTimeFormatter ENGLISH_DATE_FORMAT = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final DateTimeFormatter RU_BY_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String BY = "by";

    private LocalDate date;
    private String locale;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        String formattedDate = null;
        if(locale.isEmpty()) {
            locale = RU;
        }
        if (EN.equalsIgnoreCase(locale)) {
            formattedDate = date.format(ENGLISH_DATE_FORMAT);
        } else if(RU.equalsIgnoreCase(locale) || BY.equalsIgnoreCase(locale)) {
            formattedDate = date.format(RU_BY_DATE_FORMAT);
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(formattedDate);
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
        return super.doStartTag();
    }
}
