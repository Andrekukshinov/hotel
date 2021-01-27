<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<header>
    <ul class="topnav">
        <li><img class="logo" src="${pageContext.request.contextPath}/static/imgs/logo.bmp" width="46" height="40"
                 alt=""></li>
        <li class="center"><a href="#">HOTEL BOOKIN'.OV</a></li>
        <c:choose>
            <c:when test="${sessionScope.login != null}">
                <li class="right">
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="logout">
                        <button class="auth">
                            <fmt:message key="header.authorized.logout"/>
                        </button>
                    </form>
                </li>
                <li class="right left-border">
                    <a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=home">
                        <fmt:message key="left.user.home"/>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="right left-border ">
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="logout">
                        <button class="auth">
                            <fmt:message key="header.unauthorized.login"/>
                        </button>
                    </form>
                </li>
            </c:otherwise>
        </c:choose>

        <li class="right select-container">
            <ul>
                <li class="languages">
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="en"/>
                        <button type="submit"><fmt:message key="lang.en"/></button>
                    </form>
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="ru"/>
                        <button type="submit"><fmt:message key="lang.ru"/></button>
                    </form>
                    <form method="post"
                          action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}">
                        <input type="hidden" name="lang" value="by"/>
                        <button type="submit"><fmt:message key="lang.by"/></button>
                    </form>
                </li>
            </ul>
        </li>
    </ul>
</header>
