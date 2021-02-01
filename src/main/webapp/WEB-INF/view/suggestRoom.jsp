<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.12.2020
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="custom-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/profileStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/rooms.css">
</head>
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>

    <div class="table-wrapper">
        <div class="grid-container">
            <div class="application-item">
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
                    <li>
                        <form class="admin-users-form" method="post"
                              action="${pageContext.request.contextPath}/controller?command=admin_reject_application">
                            <input type="hidden" name="id" value="${application.id}">
                            <button type="submit" class="verdict-app-button no-style-ul"><fmt:message
                                    key="admin.user.application.reject"/></button>
                        </form>
                    </li>

                </ul>
            </div>
            <c:forEach var="room" items="${rooms}" varStatus="index">
                <div class="item">
                    <div><fmt:message key="admin.room.capacity"/> ${room.capacity}</div>
                    <div><fmt:message key="admin.room.status"/> <fmt:message
                            key="admin.room.status.${room.isAvailable}"/></div>
                    <div><fmt:message key="admin.room.price"/> ${room.price}</div>
                    <div><fmt:message key="admin.room.Number"/> ${room.number}</div>
                    <div><fmt:message key="admin.room.type"/> <fmt:message
                            key="admin.room.type.${room.roomType}"/></div>
                    <form method="POST"
                          class="margin-top"
                          action="${pageContext.request.contextPath}/controller?command=admin_approve_application">
                        <input type="hidden" value="${application.id}" name="applicationId">
                        <input type="hidden" value="APPROVED" name="applicationStatus">
                        <input type="hidden" value="${room.id}" name="roomId">
                        <button type="submit" ${(now < application.arrivalDate)? "":"disabled"}
                                class="edit-description users-submit">
                            <fmt:message key="admin.user.application.approve"/>
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <ex:pagination
                href="${pageContext.request.contextPath}/controller?command=admin_suggest_room&applicationId=${application.id}&page="
                currentPage="${page}" lastPage="${lastPage}"/>

    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
