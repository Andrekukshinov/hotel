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
</head>
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="toBeFound">
    <jsp:include page="templates/leftMenu.jsp"/>
    profile history
    <%--    todo implem--%>
    <%--    todo implem + foreach--%>

</div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
