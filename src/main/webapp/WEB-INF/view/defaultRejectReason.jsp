<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.11.2020
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</head>
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div id="room-form" class="booking-div shorter">
        <h2 class="align-center"><fmt:message key="application.reject"/> </h2>
        <p class="reject-reason"><fmt:message key="application.reject.reason"/></p>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="profileHistory">
        <button class="go-back-margin" type="submit"><fmt:message key="admin.user.application.go.back"/> </button>
        </form>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
