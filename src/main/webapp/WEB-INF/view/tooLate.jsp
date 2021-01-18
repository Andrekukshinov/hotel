<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31.12.2020
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html lang="${sessionScope.lang}">
<head>
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/rooms.css">
    <title>Login</title>
</head>
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div id="thinner" class="booking-div shorter">
        <h2 class="align-center"><fmt:message key="admin.application.late"/></h2>
        <div class="too-late">
            <ul class="no-style-ul">
                <li><fmt:message key="admin.user.application.description"/></li>
                <li><fmt:message key="admin.room.capacity"/> ${application.personAmount}</li>
                <li><fmt:message key="admin.room.type"/> <fmt:message
                        key="admin.room.type.${application.type}"/></li>
                <li><fmt:message key="booking.book.date.arrival"/>
                    <ex:date-format date="${application.arrivalDate}" locale="${sessionScope.lang}"/>
                </li>
                <li><fmt:message key="booking.book.date.leave"/>
                    <ex:date-format date="${application.leavingDate}" locale="${sessionScope.lang}"/>
                </li>
                <li><fmt:message key="admin.user.application.status"/><fmt:message
                        key="admin.user.application.status.${application.status}"/></li>

            </ul>
        </div>
        <p>The reason is: ${application.arrivalDate} is less than ${now}(current date)</p>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="admin_active_applications">
            <button class="go-back-margin" type="submit"><fmt:message
                    key="admin.user.application.go.back"/></button>
        </form>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>

</html>
