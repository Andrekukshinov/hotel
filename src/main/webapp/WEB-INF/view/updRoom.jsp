<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.11.2020
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<div id="to-be-found" class="room-update-form" style="min-height: 80%">
    <jsp:include page="templates/leftMenu.jsp"/>

    <form method="post"  id="room-form" class="booking-div"
          action="${pageContext.request.contextPath}/controller?command=admin_save_updated_room">
        <h2 class="align-center"><fmt:message key="admin.room.update"/> ${room.number}</h2>
        <p class="align-center"><fmt:message key="booking.book.select.people"/></p>
        <label class="floating-radio first-label">
            <input required name=capacity ${room.capacity == 1 ? 'checked' :''} type="radio" value="1">
            1
        </label>
        <label class="floating-radio">
            <input required name=capacity ${room.capacity == 2 ? 'checked' :''} type="radio" value="2">
            2
        </label>
        <label class="floating-radio">
            <input required name=capacity ${room.capacity == 3 ? 'checked' :''} type="radio" value="3">
            3
        </label>
        <label class="floating-radio">
            <input required name=capacity ${room.capacity == 4 ? 'checked' :''} type="radio" value="4">
            4
        </label>
        <label class="floating-radio last-label">
            <input required name=capacity ${room.capacity == 5 ? 'checked' :''} type="radio" value="5">
            5
        </label>
        <div>
            <br>
            <br>
        </div>
        <label class="input-booking" for="apartment-type"><fmt:message key="booking.book.room.type"/>
            <select required class="apartment-type" id="apartment-type" name="roomType">
                <option ${room.roomType == 'STANDARD' ? 'selected' :''} value="STANDARD">
                    <fmt:message key="booking.book.room.type.op1"/>
                </option>
                <option ${room.roomType == 'BUSINESS' ? 'selected' :''} value="BUSINESS">
                    <fmt:message key="booking.book.room.type.op2"/>
                </option>
                <option ${room.roomType == 'SKY_WALKER' ? 'selected' :''} value="SKY_WALKER">
                    <fmt:message key="booking.book.room.type.op3"/>
                </option>
                <option ${room.roomType == 'LUX' ? 'selected' :''} value="LUX">
                    <fmt:message key="booking.book.room.type.op4"/>
                </option>
            </select>
        </label>
        <div>
            <br>
            <br>
        </div>

        <input hidden disabled type="hidden" class="room-number" id="number" name="number" value="${room.number}">

        <label class="input-booking" for="price"><fmt:message key="admin.room.price"/>
            <input required class="room-price room" type="number" id="price" name="price" value="${room.price}">
        </label>
        <p class="error-message space"><fmt:message key="error.price"/></p>
        <div>
            <br>
            <br>
        </div>

        <input hidden name="id" type="hidden" value="${room.id}"/>
        <button class="login-submit" type="submit"><fmt:message key="booking.book.button.leave"/></button>
    </form>
</div>

<jsp:include page="templates/footer.jsp"/>
</body>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</html>
