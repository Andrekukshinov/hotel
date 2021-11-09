<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html lang="${param.lang}">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <title>Login</title>
</head>
<body onload="validateLogin()" class="booking">
<jsp:include page="templates/header.jsp"/>
<div class="login-form">
    <h3 class="align-center"><fmt:message key="login.writing"/></h3>
    <c:if test="${errorMassage != null}">
        <p class="error"><fmt:message key="error.user.${errorMassage}"/></p>
    </c:if>
    <form method="post" id="login-form" action="${pageContext.request.contextPath}/controller?command=login">
        <label class="login-label" for="login"><fmt:message key="login.button"/></label>
        <input required maxlength="50" class="login-text" type="text" id="login" name="login"
               placeholder="<fmt:message key="input.name"/>">
        <p class="error-message space"><fmt:message key="error.login.with.spaces"/></p>
        <label class="login-label" for="pass"><fmt:message key="pass"/></label>
        <input required maxlength="50" class="login-text" type="password" id="pass" name="password"
               placeholder="<fmt:message key="input.pass"/>">
        <p class="error-message space"><fmt:message key="error.password.with.spaces"/></p>
        <button class="login-submit" id="submit" type="submit"><fmt:message key="submit"/></button>
    </form>
    <p>new cumy</p>
</div>
<jsp:include page="templates/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
