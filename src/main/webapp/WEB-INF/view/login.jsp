<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="ru" />
<%--<fmt:setLocale value="${param.lang}" />--%>
<fmt:setBundle basename="locale"/>
<html lang="${param.lang}">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <title>Login</title>
</head>
<body class="stdColor">
<jsp:include page="templates/header.jsp"/>
<div class="loginForm">
    <h3 class="alignCenter"><fmt:message key="login.writing"/></h3>
    <p class="error">${errorMassage}</p>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=login">
        <label class="loginLabel" for="fname"><fmt:message key="login.button"/></label>
        <input class="loginText" type="text" id="fname" name="login" placeholder="<fmt:message key="input.name"/>">

        <label class="loginLabel" for="lname"><fmt:message key="pass"/></label>
        <input class="loginText" type="password" id="lname" name="password"
               placeholder="<fmt:message key="input.pass"/>">

        <button class="loginSubmit" type="submit"><fmt:message key="submit"/></button>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
