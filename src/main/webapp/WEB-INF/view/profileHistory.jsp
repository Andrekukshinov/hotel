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
<body class="booking" onload="confirmDisable()">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="table-wrapper">
        <table id="applications">
            <tr>
                <th>#</th>
                <th class="small-font"><fmt:message key="admin.user.application.description"/></th>
                <th><fmt:message key="history.user.application.status"/></th>
            </tr>
            <c:choose>
                <c:when test="${lastPage != 0}">
                    <c:forEach var="applicationDto" items="${applications}" varStatus="index">
                        <tr>
                            <td>${(itemsPerPage)*(page - 1) + index.count}</td>

                            <td class="small-font">
                                <ul class="no-style-ul">
                                    <li>
                                        <fmt:message key="admin.room.capacity"/> ${applicationDto.personAmount}
                                    </li>
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
                                <div class="small-font">
                                    <fmt:message key="admin.user.application.status.${applicationDto.status}"/>
                                </div>
                                <c:choose>
                                    <c:when test="${applicationDto.status == 'DENIED'}">
                                        <br>
                                        <a class="check-the-bill-button small-font open-modal"
                                           href="${pageContext.request.contextPath}/controller?command=reject_reason">
                                            <fmt:message key="history.user.reject.details"/>
                                        </a>
                                        <br>
                                    </c:when>
                                    <c:when test="${applicationDto.status == 'IN_ORDER'}">
                                        <form class="admin-users-form">
                                            <button type="button" class="check-the-bill-button no-style-ul open-modal">
                                                <fmt:message key="history.application.cancel"/>
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:when test="${applicationDto.status == 'APPROVED'}">
                                        <br>
                                        <a class="check-the-bill-button small-font open-modal"
                                           href="${pageContext.request.contextPath}/controller?command=user_bill&id=${applicationDto.id}">
                                            <fmt:message key="history.check.bill"/>
                                        </a>
                                        <br>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" class="open-modal"/>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <div id="myModal${index.count}" class="modal">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <span class="close">&times;</span>
                                    <h2>  <fmt:message key="history.user.modal.title"/></h2>
                                </div>
                                <div class="modal-body">
                                    <p><fmt:message key="history.user.modal.massage"/></p>
                                </div>
                                <div class="modal-footer">
                                    <p>
                                    <ul class="no-style-ul">
                                        <li class="first-option">
                                            <form class="admin-users-form">
                                                <button type="button" class="users-submit reject">
                                                    <fmt:message key="history.application.cancel"/>
                                                </button>
                                            </form>
                                        </li>
                                        <li class="first-option">
                                            <form class="admin-users-form" method="post"
                                                  action="${pageContext.request.contextPath}/controller?command=user_cancel_order">
                                                <input type="hidden" name="id" value="${applicationDto.id}">
                                                <input type="hidden" name="page" value="${page}">
                                                <button type="submit" class="users-submit">
                                                    <fmt:message key="admin.room.change.true"/>
                                                </button>
                                            </form>
                                        </li>
                                    </ul>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <td>----</td>
                    <td>----</td>
                    <td>----</td>
                </c:otherwise>
            </c:choose>
        </table>
        <ex:pagination href="${pageContext.request.contextPath}/controller?command=profileHistory&page="
                       currentPage="${page}" lastPage="${lastPage}"/>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>

</body>
</html>
