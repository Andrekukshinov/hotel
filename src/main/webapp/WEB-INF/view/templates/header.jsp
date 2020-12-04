<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<%--<fmt:setLocale value="en"/>--%>
<fmt:setBundle basename="locale"/>

<header>
    <ul class="topnav">
        <li><img class="logo" src="${pageContext.request.contextPath}/static/imgs/logo.bmp" width="46" height="40"
                 alt=""></li>
        <li class="center"><a href="#contact">HOTEL BOOKIN'.OV</a></li>
        <li class="right "><a href="${pageContext.request.contextPath}/logout"><fmt:message
                key="header.authorized.logout"/></a></li>
        <li class="right"><a href="${pageContext.request.contextPath}/controller?command=home"><fmt:message
                key="header.authorized.profile"/></a></li>
        <li class="right left-border"><a href="${pageContext.request.contextPath}/controller?command=booking">
            <fmt:message key="header.authorized.booking"/>
        </a></li>
        <li class="right select-container">
            <ul>
                <!--todo add to properties lang-->
                <li class="languages">
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="en"/>
                        <button type="submit">en</button>
                    </form>
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="ru"/>
                        <button type="submit">ru</button>
                    </form>
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="by"/>
                        <button type="submit">by</button>
                    </form>
                </li>
            </ul>
        </li>
    </ul>
</header>

