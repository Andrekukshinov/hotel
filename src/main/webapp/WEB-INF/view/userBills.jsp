<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.11.2020
  Time: 19:41
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/applications.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/rooms.css">
</head>
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="table-wrapper">
        <table id="applications">
            <tr>
                <th>#</th>
                <th class="small-font"><fmt:message key="admin.user.application.description"/></th>
                <th><fmt:message key="user.bills.bill"/></th>
            </tr>
            <c:choose>
                <c:when test="${lastPage != 0}">
                    <c:forEach var="applicationDto" items="${applications}" varStatus="index">
                        <tr>
                            <td>${(itemsPerPage)*(page - 1) + index.count}</td>

                            <td class="small-font">
                                <ul class="no-style-ul">
                                    <li><fmt:message key="admin.room.capacity"/> ${applicationDto.personAmount}</li>
                                    <li><fmt:message key="admin.room.type"/> <fmt:message
                                            key="admin.room.type.${applicationDto.type}"/></li>
                                    <li><fmt:message key="booking.book.date.arrival"/>
                                        <ex:date-format date="${applicationDto.arrivalDate}"
                                                        locale="${sessionScope.lang}"/>
                                    </li>
                                    <li><fmt:message key="booking.book.date.leave"/>
                                        <ex:date-format date="${applicationDto.leavingDate}"
                                                        locale="${sessionScope.lang}"/>
                                    </li>
                                </ul>
                            </td>
                            <td>
                                <br>
                                <a class="check-the-bill-button small-font"
                                   href="${pageContext.request.contextPath}/controller?command=user_bill&id=${applicationDto.id}">
                                    <fmt:message key="history.check.bill"/>
                                </a>
                                <br>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <td>----</td>
                    <td>----</td>
                    <td>----</td>
                </c:otherwise>
            </c:choose>
        </table>
        <ex:pagination href="${pageContext.request.contextPath}/controller?command=user_bills&page="
                       currentPage="${page}" lastPage="${lastPage}"/>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
