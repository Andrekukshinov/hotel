<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.12.2020
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <style>
        ul.l {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        /**/
        li.l {
            float: left;
            background-color: #dddddd;
        }

        /**/
    </style>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/profileStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/rooms.css">
</head>
<body class="body">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="table-wrapper">
        <div class="grid-container">
            <div class="application-item">
                <ul class="l">
                    <li>User application description</li>
                    <li>person amount: ${application.personAmount}</li>
                    <li>apartment type: ${application.type}</li>
                    <li>arrival date: ${application.arrivalDate}</li>
                    <li>leaving date: ${application.leavingDate}</li>
                    <li>status: ${application.status}</li>
                </ul>
            </div>
            <%----%>
            <c:forEach var="room" items="${rooms}" varStatus="index">
                <div class="item">
                    <div><fmt:message key="admin.room.capacity"/> ${room.capacity}</div>
                    <div><fmt:message key="admin.room.status"/> <fmt:message
                            key="admin.room.status.${room.roomStatus}"/></div>
                    <div><fmt:message key="admin.room.price"/> ${room.price}</div>
                    <div><fmt:message key="admin.room.Number"/> ${room.number}</div>
                    <div><fmt:message key="admin.room.type"/> <fmt:message
                            key="admin.room.type.${room.roomType}"/></div>
                    <div>
                        <img src="${pageContext.request.contextPath}/static/imgs/download.jpg" height="200"
                             width="220"/>
                    </div>
                    <form method="POST" action="${pageContext.request.contextPath}/controller?command=admin_approve_application">
                        <input type="hidden" value="${application.id}" name="applicationId">
                        <input type="hidden" value="OCCUPIED" name="roomStatus">
                        <input type="hidden" value="APPROVED" name="applicationStatus">
                        <input type="hidden" value="${room.id}" name="roomId">
                        <button type="submit" class="edit-description users-submit">
                            <fmt:message key="submit"/>
                        </button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
