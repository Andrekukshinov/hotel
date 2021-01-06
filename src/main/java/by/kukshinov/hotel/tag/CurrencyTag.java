package by.kukshinov.hotel.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyTag extends TagSupport implements Tag {
    private static final String PATTERN = ".{3}|(.{1,2}$)";
    private static final String SPACE = " ";
    private static final String DOLLAR = "$";
    private String money;

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    @Override
    public int doStartTag() throws JspException{
        JspWriter writer = pageContext.getOut();
        int length = money.length();
        if (length <= 3) {
            printResult(writer, money);
        } else {
            parseLongerValue(writer);
        }
        return SKIP_BODY;
    }

    private void printResult(JspWriter writer, String writing) throws JspException {
        try {
            writer.write(writing + DOLLAR);
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }
    }

    private void parseLongerValue(JspWriter writer) throws JspException {
        StringBuilder reversedResult = new StringBuilder();
        String reversed = new StringBuilder(money).reverse().toString();

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(reversed);

        while (matcher.find()){
            reversedResult.append(matcher.group());
            reversedResult.append(SPACE);
        }

        StringBuilder result = reversedResult.reverse();
        printResult(writer, result.toString());
    }
}
