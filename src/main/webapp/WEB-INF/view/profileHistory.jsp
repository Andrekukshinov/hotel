<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.11.2020
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th><fmt:message key="history.user.application.status"/> </th>
            </tr>
            <c:forEach var="application" items="${applications}" varStatus="index">
                <tr>
                    <td>${(itemsPerPage)*(page - 1) + index.count}</td>

                    <td class="small-font">
                        <ul class="no-style-ul">
                            <li><fmt:message key="admin.room.capacity"/> ${application.personAmount}</li>
                            <li><fmt:message key="admin.room.type"/> <fmt:message
                                    key="admin.room.type.${application.type}"/></li>
                            <li><fmt:message key="booking.book.date.arrival"/> ${application.arrivalDate}</li>
                            <li><fmt:message key="booking.book.date.leave"/> ${application.leavingDate}</li>
                        </ul>
                    </td>
                    <td>
                        <div class="small-font">
                            <fmt:message key="admin.user.application.status.${application.status}"/>
                        </div>
                        <br>
                        <c:if test="${application.status == 'APPROVED'}">
                            <a class="check-the-bill-button small-font"
                               href="${pageContext.request.contextPath}/controller?command=user_bill&id=${application.id}">
                                <fmt:message key="history.check.bill"/>
                            </a>
                        </c:if>
                        <br>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%--        todo correct pagination--%>
        <div class="pages">
            <c:choose>
                <c:when test="${(page - 1) == 0}">
                    <a href="" type="submit" class="pagination-children">❮</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/controller?command=profileHistory&page=${page-1}"
                       type="submit"
                       class="pagination-children">❮</a>
                </c:otherwise>
            </c:choose>
            <div class="pagination-children">${page}</div>
            <c:choose>
                <c:when test="${applications.size() != itemsPerPage}">
                    <a href="" type="submit" class="pagination-children">❯</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/controller?command=profileHistory&page=${page+1}"
                       type="submit"
                       class="pagination-children">❯</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
