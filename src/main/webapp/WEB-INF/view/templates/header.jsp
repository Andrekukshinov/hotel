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
                <li class="right ">
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="logout">
                        <button class="auth">
                            <fmt:message key="header.unauthorized.login"/>
                        </button>
                    </form>
                </li>
            </c:otherwise>
        </c:choose>

        <c:if test="${sessionScope.login == null}">
            <li class="right left-border">
                <form action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="logout">
                    <button class="auth">
                        <fmt:message key="header.unauthorized.signup"/>
                    </button>
                </form>
            </li>
        </c:if>

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


<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<header>
    <ul class="topnav">
        <li>
            <img class="logo" src="${pageContext.request.contextPath}/static/imgs/logo.bmp" width="46" height="40"
                 alt="">
        </li>
        <li class="center"><a href="#">HOTEL BOOKIN'.OV</a></li>
        <c:choose>
            <c:when test="${sessionScope.login != null}">
                <li class="right ">
                    <a href="${pageContext.request.contextPath}/controller?command=logout">
                        <fmt:message key="header.authorized.logout"/>
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="right ">
                    <a href="${pageContext.request.contextPath}/controller?command=login">
                        <fmt:message key="header.unauthorized.login"/>
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.login == null}">
            <li class="right left-border">
                <a><fmt:message key="header.unauthorized.signup"/></a>
            </li>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.login != null && sessionScope.role == 'USER'}">
                <li class="right left-border">
                    <a href="${pageContext.request.contextPath}/controller?command=booking">
                        <fmt:message key="header.authorized.booking"/>
                    </a>
                </li>
            </c:when>
            <c:when test="${sessionScope.login != null }">
                <li>
                    <a class="right left-border" href="${pageContext.request.contextPath}/controller?command=admin_create_room">
                        <fmt:message key="left.admin.room.add"/>
                    </a>
                </li>
            </c:when>
        </c:choose>


    </ul>
</header>

--%>
