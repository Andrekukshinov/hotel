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
<body class="booking" onload="confirmDisable()">
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

                    <ul class="no-style-ul">
                        <li class="no-style-ul">
                            <c:if test="${room.isAvailable == false}">
                                <form class="admin-users-form" method="get"
                                      action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="admin_update_room">
                                    <input type="hidden" name="id" value="${room.id}">
                                    <button type="submit" class="users-submit full-width">
                                        <fmt:message key="admin.room.button.edit"/>
                                    </button>
                                </form>
                            </c:if>
                        </li>
                        <li>
                            <form class="admin-users-form" method="post"
                                  action="${pageContext.request.contextPath}/controller?command=admin_change_room_activity">
                                <input type="hidden" name="id" value="${room.id}">
                                <c:choose>
                                    <c:when test="${room.isAvailable == true}">
                                        <button type="button" class="verdict-app-button open-modal">
                                            <fmt:message key="admin.room.change.${room.isAvailable}"/>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="verdict-app-button open-modal">
                                            <fmt:message key="admin.room.change.${room.isAvailable}"/>
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </form>

                        </li>
                    </ul>
                </div>
                <div id="myModal${index.count}" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <span class="close">&times;</span>
                            <h2><fmt:message key="bill.user.room.description"/> ${room.number}</h2>
                        </div>
                        <div class="modal-body">
                            <p>
                                <fmt:message key="admin.room.modal.confirm.message"/>
                            </p>
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
                                          action="${pageContext.request.contextPath}/controller?command=admin_change_room_activity">
                                        <input type="hidden" name="id" value="${room.id}">
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

        </div>
        <ex:pagination href="${pageContext.request.contextPath}/controller?command=admin_rooms&page="
                       currentPage="${page}" lastPage="${lastPage}"/>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>

