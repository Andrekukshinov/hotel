<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.12.2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "ex" uri = "custom-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/profileStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/rooms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bill.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
</head>
<body class="body">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="user-bill">
        <div class="bill-container">
            <div class="item">
                <h3><fmt:message key="bill.user.room.description"/></h3>
                <div><fmt:message key="admin.room.capacity"/> ${applicationRoom.room.capacity}</div>
                <div><fmt:message key="admin.room.Number"/> ${applicationRoom.room.number}</div>
                <div><fmt:message key="admin.room.type"/> <fmt:message
                        key="admin.room.type.${applicationRoom.room.roomType}"/></div>
            </div>
            <div class="item bill-application-font">
                <h3><fmt:message key="bill.user.application.description"/></h3>
                <ul class=" no-style-ul">
                    <li><fmt:message key="admin.room.capacity"/> ${applicationRoom.application.personAmount}</li>
                    <li><fmt:message key="admin.room.type"/> <fmt:message
                            key="admin.room.type.${applicationRoom.application.type}"/></li>
                    <li><fmt:message key="booking.book.date.arrival"/> ${applicationRoom.application.arrivalDate}</li>
                    <li><fmt:message key="booking.book.date.leave"/> ${applicationRoom.application.leavingDate}</li>

                </ul>
                <fmt:message key="bill.total.price"/><ex:money-format money="${applicationRoom.totalCost}"/>
            </div>
            <div class="item bill-application-font">
                <form class="admin-users-form  margin-bill-form" method="post"
                      action="${pageContext.request.contextPath}/controller?command=user_reject_application">
                    <input type="hidden" value="${applicationRoom.application.id}" name="applicationId">
                    <button type="submit" class="history-button two-options"><fmt:message key="bill.deny"/></button>
                </form>
                <p class="error">${tooLate}</p>
            </div>
            <div class="item">
                <a class="verdict-app-button user-approve-link"
                   href="${pageContext.request.contextPath}/controller?command=profileHistory"> <fmt:message key="bill.back"/>
                </a>
            </div>
        </div>
    </div>

</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>

