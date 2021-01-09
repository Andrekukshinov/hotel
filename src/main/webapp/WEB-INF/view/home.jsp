<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.11.2020
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">

</head>
<body class="body">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div>
        <img src="${pageContext.request.contextPath}/static/imgs/hotel.jpg" height="500"
             width="100%"/>
        <p class="home-welcome">
            <fmt:message key="home.welcome"/> ${login}
        </p>
        <p class="home-txt">
            <fmt:message key="home.long.txt"/>
        </p>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>

</body>
</html>
