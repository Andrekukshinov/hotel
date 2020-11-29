<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${param.lang}"/>
<%--<fmt:setLocale value="en"/>--%>
<fmt:setBundle basename="locale"/>

<header>
    <ul class="topnav">
        <li><img class="logo" src="${pageContext.request.contextPath}/static/imgs/logo.bmp" width="46" height="40" alt=""></li>
        <li class="center"><a href="#contact">HOTEL BOOKIN'.OV</a></li>
        <li class="right "><a href="${pageContext.request.contextPath}/"><fmt:message key="header.authorized.logout"/></a></li>
        <li class="right"><a href="${pageContext.request.contextPath}/controller?command=home"><fmt:message key="header.authorized.profile"/></a></li>
        <li class="right leftBorder"><a href="${pageContext.request.contextPath}/controller?command=booking">
            <fmt:message key="header.authorized.booking"/>
        </a></li>
        <li class="right  selectContainer">
            <select class="selectLang " id="language">
                <option value="en">en</option>
                <option value="by">by</option>
                <option value="ru">ru</option>
            </select>
        </li>
    </ul>
</header>

