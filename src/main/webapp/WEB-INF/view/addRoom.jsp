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
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</head>
<body onload="validateNewRoom()" class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <form method="post" id="room-form" class="booking-div"
          action="${pageContext.request.contextPath}/controller?command=admin_add_room">
        <h2 class="align-center"><fmt:message key="admin.room.create"/></h2>
        <p class="align-center"><fmt:message key="booking.book.select.people"/></p>
        <label class="floating-radio first-label">
            <input required name=capacity type="radio" value="1">
            1
        </label>
        <label class="floating-radio">
            <input required name=capacity type="radio" value="2">
            2
        </label>
        <label class="floating-radio">
            <input required name=capacity type="radio" value="3">
            3
        </label>
        <label class="floating-radio">
            <input required name=capacity type="radio" value="4">
            4
        </label>
        <label class="floating-radio last-label">
            <input required name=capacity type="radio" value="5">
            5
        </label>
        <div>
            <br>
            <br>
        </div>
        <label class="input-booking" for="apartment-type"><fmt:message key="booking.book.room.type"/>
            <select required class="apartment-type" id="apartment-type" name="apartment-type">
                <option value="STANDARD"><fmt:message key="booking.book.room.type.op1"/></option>
                <option value="BUSINESS"><fmt:message key="booking.book.room.type.op2"/></option>
                <option value="SKY_WALKER"><fmt:message key="booking.book.room.type.op3"/></option>
                <option value="LUX"><fmt:message key="booking.book.room.type.op4"/></option>
            </select>
        </label>
        <div>
            <br>
            <br>
        </div>
        <label class="input-booking" for="number"><fmt:message key="admin.room.create.room"/>
            <input maxlength="7" required class="room-number room" type="number" id="number" name="number">
        </label>
        <p class="error-message space"><fmt:message key="error.number"/></p>
        <c:if test="${error != null}">
            <p class="error space">${number} <fmt:message key="error.number.exists"/></p>
        </c:if>
        <div>
            <br>
            <br>
        </div>
        <label class="input-booking" for="price"><fmt:message key="admin.room.price"/>
            <input required class="room-price room" type="number" id="price" name="price">
        </label>
        <p class="error-message space"><fmt:message key="error.number"/></p>
        <div>
            <br>
            <br>
        </div>
        <button class="login-submit" type="submit"><fmt:message key="booking.book.button.leave"/></button>
    </form>
</div>

<jsp:include page="templates/footer.jsp"/>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>
