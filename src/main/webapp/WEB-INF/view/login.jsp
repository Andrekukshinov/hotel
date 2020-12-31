<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html lang="${param.lang}">
<head>
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <title>Login</title>
</head>
<body class="standard-color">
<jsp:include page="templates/header.jsp"/>
<div class="login-form">
    <h3 class="align-center"><fmt:message key="login.writing"/></h3>
    <p class="error">${errorMassage}</p>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=login">
        <label class="login-label" for="fname"><fmt:message key="login.button"/></label>
        <input class="login-text" type="text" id="fname" name="login" placeholder="<fmt:message key="input.name"/>">
        <label class="login-label" for="lname"><fmt:message key="pass"/></label>
        <input class="login-text" type="password" id="lname" name="password"
               placeholder="<fmt:message key="input.pass"/>">
        <button class="login-submit" type="submit"><fmt:message key="submit"/></button>
    </form>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
