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
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
        <form method="post" class="booking-div" action="${pageContext.request.contextPath}/controller?command=admin_add_room">
            <h2 class="align-center"><fmt:message key="admin.room.create"/></h2>
            <p class="align-center"><fmt:message key="booking.book.select.people"/></p>
            <label class="floating-radio first-label">
                <input name=capacity type="radio" value="1">
                1
            </label>
            <label class="floating-radio">
                <input name=capacity type="radio" value="2">
                2
            </label>
            <label class="floating-radio" >
                <input name=capacity type="radio" value="3">
                3
            </label >
            <label class="floating-radio" >
                <input name=capacity type="radio" value="4">
                4
            </label>
            <label class="floating-radio last-label">
                <input name=capacity type="radio" value="5">
                5
            </label>
            <div>
                <br>
                <br>
            </div>
            <label class="input-booking" for="apartment-type"><fmt:message key="booking.book.room.type"/>
                <select class="apartment-type" id="apartment-type" name="apartment-type">
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
                <input class="room-number" type="number" id="number" name="number">
            </label>
            <div>
                <br>
                <br>
            </div>
            <label class="input-booking" for="price"><fmt:message key="admin.room.price"/>
                <input class="room-price" type="number" id="price" name="price">
            </label>
            <div>
                <br>
                <br>
            </div>
            <button class="login-submit" type="submit"><fmt:message key="booking.book.button.leave"/></button>
        </form>
    </div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
