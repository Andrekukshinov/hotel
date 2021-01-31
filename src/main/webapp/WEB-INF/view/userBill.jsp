<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.12.2020
  Time: 15:48
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
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/profileStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/rooms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bill.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
</head>
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="user-bill">
        <div class="bill-container">
            <div class="item">
                <h3><fmt:message key="bill.user.room.description"/></h3>
                <c:choose>
                    <c:when test="${isRejectable || role =='ADMIN'}">
                        <div>
                            <fmt:message key="admin.room.capacity"/>
                                ${application.room.capacity}
                        </div>
                        <div>
                            <fmt:message key="admin.room.Number"/>
                                ${application.room.number}
                        </div>
                        <div>
                            <fmt:message key="admin.room.type"/>
                            <fmt:message key="admin.room.type.${application.room.roomType}"/>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <fmt:message key="history.user.past.application"/> ${application.room.number}
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="item bill-application-font">
                <h3><fmt:message key="bill.user.application.description"/></h3>
                <ul class=" no-style-ul">
                    <c:if test="${role =='ADMIN'}">
                        <li><fmt:message key="admin.user.login"/> ${login}</li>
                        <li><fmt:message key="admin.room.status"/> <fmt:message
                                key="admin.user.application.status.${application.application.status}"/></li>
                    </c:if>
                    <li><fmt:message key="admin.room.capacity"/> ${application.application.personAmount}</li>
                    <li><fmt:message key="admin.room.type"/> <fmt:message
                            key="admin.room.type.${application.application.type}"/></li>
                    <li><fmt:message key="booking.book.date.arrival"/>
                        <ex:date-format date="${application.application.arrivalDate}" locale="${sessionScope.lang}"/>
                    </li>
                    <li><fmt:message key="booking.book.date.leave"/>
                        <ex:date-format date="${application.application.leavingDate}" locale="${sessionScope.lang}"/>
                    </li>
                </ul>
                <fmt:message key="bill.total.price"/><ex:money-format money="${application.application.totalPrice}"/>
            </div>
            <c:if test="${role !='ADMIN' && isRejectable}">
                <div class="history-item-button bill-application-font">
                    <form class="admin-users-form  margin-bill-form" method="post"
                          action="${pageContext.request.contextPath}/controller?command=user_reject_application">
                        <input type="hidden" value="${application.application.id}" name="applicationId">
                        <input type="hidden" value="${application.room.id}" name="roomId">
                        <button type="submit" class="history-button"><fmt:message key="bill.deny"/></button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>

</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>

