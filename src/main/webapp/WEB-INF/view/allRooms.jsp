<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.12.2020
  Time: 14:23
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/profileStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/rooms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
</head>
<body class="booking" onload="edit(); validateRoomUpdate()">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="table-wrapper">
        <div class="grid-container">
            <c:forEach var="room" items="${rooms}" varStatus="index">
                <div class="item">
                    <div>
                        <fmt:message key="admin.room.capacity"/> ${room.capacity}
                    </div>
                    <div>
                        <fmt:message key="admin.room.status"/>
                        <fmt:message key="admin.room.status.${room.isAvailable}"/>
                    </div>
                    <div>
                        <fmt:message key="admin.room.price"/><ex:money-format money="${room.price}"/>
                    </div>
                    <div>
                        <fmt:message key="admin.room.Number"/> ${room.number}
                    </div>
                    <div>
                        <fmt:message key="admin.room.type"/>
                        <fmt:message key="admin.room.type.${room.roomType}"/>
                    </div>
<%--                    <form class="admin-users-form" method="get" action="${pageContext.request.contextPath}/controller">--%>
<%--                        <input type="hidden" name="command" value="admin_update_room">--%>
<%--                        <input type="hidden" name="id" value="${room.id}">--%>
<%--                        <button class="room-update" type="submit">--%>
<%--                            <fmt:message key="admin.room.button.edit"/>--%>
<%--                        </button>--%>
<%--                    </form>--%>
<%--                    <form class="admin-users-form" method="get" action="${pageContext.request.contextPath}/controller">--%>
<%--                        <input type="hidden" name="command" value="admin_update_room">--%>
<%--                        <input type="hidden" name="id" value="${room.id}">--%>
<%--                        <button class="room-update" type="submit">--%>
<%--                            <fmt:message key="admin.room.button.edit"/>--%>
<%--                        </button>--%>
<%--                    </form>--%>
                        <ul class="no-style-ul">
                            <li class="first-option">
                                <form  class="admin-users-form" method="get" action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="admin_update_room">
                                    <input type="hidden" name="id" value="${room.id}">
                                    <button type="submit" class="verdict-app-button two-options"><fmt:message key="admin.room.button.edit"/></button>
                                </form>
                            </li>
                            <li class="first-option">
                                <form  class="admin-users-form" method="post" action="${pageContext.request.contextPath}/controller?command=admin_change_room_activity">
                                    <input type="hidden" name="id" value="${room.id}">
                                    <input type="hidden" name="isAvailable" value="${!room.isAvailable}">
                                    <button type="submit" class="verdict-app-button "><fmt:message key="admin.room.change.${room.isAvailable}"/></button>
                                </form>
                            </li>
                        </ul>
                </div>
            </c:forEach>

        </div>
        <ex:pagination href="${pageContext.request.contextPath}/controller?command=admin_rooms&page=" currentPage="${page}" lastPage="${lastPage}"/>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
