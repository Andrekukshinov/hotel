<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.12.2020
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@page isErrorPage="true" %>
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
<jsp:include page="/WEB-INF/view/templates/header.jsp"/>
<h1 class="error-page-massage error-page-code"> 403 </h1>
<h3 class="error-page-massage"><fmt:message key="error.page.403"/></h3>
<div class="error-page-img-div">
    <img src="${pageContext.request.contextPath}/static/imgs/403.jpg" width="90%" height="70%"/>
</div>
<jsp:include page="/WEB-INF/view/templates/footer.jsp"/>
</body>
</html>
