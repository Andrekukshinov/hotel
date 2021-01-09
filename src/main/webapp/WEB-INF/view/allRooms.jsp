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
</head>
<body class="body" onload="edit(); validateRoomUpdate()">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="table-wrapper">
        <div class="grid-container">
            <c:forEach var="room" items="${rooms}" varStatus="index">
                <div class="item">
                    <div><fmt:message key="admin.room.capacity"/> ${room.capacity}</div>
                    <div><fmt:message key="admin.room.status"/> <fmt:message
                            key="admin.room.status.${room.roomStatus}"/></div>
                    <div><fmt:message key="admin.room.price"/><ex:money-format money="${room.price}"/></div>
                    <div><fmt:message key="admin.room.Number"/> ${room.number}</div>
                    <div><fmt:message key="admin.room.type"/> <fmt:message
                            key="admin.room.type.${room.roomType}"/></div>
                    <button type="button" class="edit-description users-submit"><fmt:message
                            key="admin.room.button.edit"/></button>
                    <form method="post" action="${pageContext.request.contextPath}/controller?command=admin_update_room"
                          id="description${index.count}" class="modal">
                        <div class="inner-modal">
                            <button type="button" class="modal-close">X</button>
                            <div><fmt:message key="admin.room.capacity"/>
                                <label class="floating-radio first-label">
                                    <input name=personAmount type="radio" value="1">
                                    1
                                </label>
                                <label class="floating-radio">
                                    <input name=personAmount type="radio" value="2">
                                    2
                                </label>
                                <label class="floating-radio">
                                    <input name=personAmount type="radio" value="3">
                                    3
                                </label>
                                <label class="floating-radio">
                                    <input name=personAmount type="radio" value="4">
                                    4
                                </label>
                                <label class="floating-radio last-label">
                                    <input name=personAmount type="radio" value="5">
                                    5
                                </label>
                            </div>
                            <div><fmt:message key="admin.room.status"/>
                                <label class="floating-radio first-label">
                                    <input name=roomStatus type="radio" value="OCCUPIED">
                                    <fmt:message key="admin.room.status.OCCUPIED"/>
                                </label>
                                <label class="floating-radio">
                                    <input name=roomStatus type="radio" value="DISABLED">
                                    <fmt:message key="admin.room.status.DISABLED"/>
                                </label>
                                <label class="floating-radio">
                                    <input name=roomStatus type="radio" value="AVAILABLE">
                                    <fmt:message key="admin.room.status.AVAILABLE"/>
                                </label>

                            </div>
                            <div><fmt:message key="admin.room.price"/>
                                <label class="floating-radio last-label">
                                    <input name=price required id="price${index.count}" type="number">
                                </label>
                                <p id="error-price${index.count}" class="error-message space"><fmt:message key="error.price"/></p>
                            </div>
                            <div><fmt:message key="admin.room.type"/>
                                <label class="floating-radio first-label">
                                    <input name=roomType type="radio" value="STANDARD">
                                    <fmt:message key="admin.room.type.STANDARD"/>
                                </label>
                                <label class="floating-radio">
                                    <input name=roomType type="radio" value="BUSINESS">
                                    <fmt:message key="admin.room.type.BUSINESS"/>
                                </label>
                                <label class="floating-radio">
                                    <input name=roomType type="radio" value="SKY_WALKER">
                                    <fmt:message key="admin.room.type.SKY_WALKER"/>
                                </label>
                                <label class="floating-radio">
                                    <input name=roomType type="radio" value="LUX">
                                    <fmt:message key="admin.room.type.LUX"/>
                                </label>
                            </div>
                            <input type="hidden" name="id" value="${room.id}">
                            <input type="hidden" name="roomNumber" value="${room.number}">
                            <button class="room-update" type="submit"><fmt:message key="submit"/></button>
                        </div>
                    </form>
                </div>
            </c:forEach>

        </div>
        <div class="pages">
            <c:choose>
                <c:when test="${(page - 1) == 0}">
                    <a href="" type="submit" class="pagination-children">❮</a>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_rooms&page=1"
                       type="submit"
                       class="pagination-children active">1</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_rooms&page=${page-1}"
                       type="submit"
                       name="+" class="pagination-children">❮</a>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_rooms&page=1"
                       type="submit"
                       class="pagination-children">1</a>
                </c:otherwise>
            </c:choose>
            <c:if test="${((page - 1) != 0 ) && (page != lastPage)}">
                <div class="pagination-children">...</div>
                <div class="pagination-children active">${page}</div>
            </c:if>
            <c:choose>
                <c:when test="${lastPage == 1 }">
                    <a href="" type="submit" class="pagination-children">❯</a>
                </c:when>
                <c:when test="${page == lastPage}">
                    <div class="pagination-children">...</div>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_rooms&page=${lastPage}"
                       type="submit"
                       class="pagination-children active">${lastPage}</a>
                    <a href="" type="submit" class="pagination-children">❯</a>
                </c:when>
                <c:otherwise>
                    <div class="pagination-children">...</div>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_rooms&page=${lastPage}"
                       type="submit"
                       class="pagination-children ">${lastPage}</a>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_rooms&page=${page+1}"
                       type="submit"
                       class="pagination-children">❯</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
