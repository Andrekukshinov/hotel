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
                <div><fmt:message key="admin.room.capacity"/> ${application.room.capacity}</div>
                <div><fmt:message key="admin.room.Number"/> ${application.room.number}</div>
                <div><fmt:message key="admin.room.type"/> <fmt:message
                        key="admin.room.type.${application.room.roomType}"/></div>
            </div>
            <div class="item bill-application-font">
                <h3><fmt:message key="bill.user.application.description"/></h3>
                <ul class=" no-style-ul">
                    <li><fmt:message key="admin.room.capacity"/> ${application.application.personAmount}</li>
                    <li><fmt:message key="admin.room.type"/> <fmt:message
                            key="admin.room.type.${application.application.type}"/></li>
                    <li><fmt:message key="booking.book.date.arrival"/> ${application.application.arrivalDate}</li>
                    <li><fmt:message key="booking.book.date.leave"/> ${application.application.leavingDate}</li>

                </ul>
                <fmt:message key="bill.total.price"/><ex:money-format money="${application.application.totalPrice}"/>
            </div>
            <div class="item bill-application-font">
                <form class="admin-users-form  margin-bill-form" method="post"
                      action="${pageContext.request.contextPath}/controller?command=user_reject_application">
                    <input type="hidden" value="${application.application.id}" name="applicationId">
                    <input type="hidden" value="${application.room.id}" name="roomId">
                    <button type="submit" class="history-button two-options"><fmt:message key="bill.deny"/></button>
                </form>
                <c:if test="${tooLate != null}">
                    <p class="error"><fmt:message key="error.reject.date.${tooLate}"/></p>
                </c:if>
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

