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
        <form method="post" class="booking-div" action="${pageContext.request.contextPath}/controller?command=bookRoom">
            <h2 class="align-center"><fmt:message key="booking.book.room"/></h2>
            <p class="align-center"><fmt:message key="booking.book.select.people"/></p>
            <label class="floating-radio first-label">
                <input name=personAmount type="radio" value="1">
                1
            </label>
            <label class="floating-radio">
                <input name=personAmount type="radio" value="2">
                2
            </label>
            <label class="floating-radio" >
                <input name=personAmount type="radio" value="3">
                3
            </label >
            <label class="floating-radio" >
                <input name=personAmount type="radio" value="4">
                4
            </label>
            <label class="floating-radio last-label">
                <input name=personAmount type="radio" value="5">
                5
            </label>
            <div>
                <br>
                <br>
            </div>
            <label class="input-booking" for="rooms"><fmt:message key="booking.book.room.type"/>
                <select class="apartment-type" id="rooms" name="apartment">
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
            <label class="input-booking" for="arrivalDate"><fmt:message key="booking.book.date.arrival"/>
                <input class="date-input-arr" type="date" id="arrivalDate" name="arrivalDate">
            </label>
            <div>
                <br>
                <br>
            </div>
            <label class="input-booking" for="leavingDate"><fmt:message key="booking.book.date.leave"/>
                <input class="date-input-leave" type="date" id="leavingDate" name="leavingDate">
            </label>
            <div>
                <br>
                <br>
            </div>
            <input type="hidden" name="state" value="IN_ORDER">
            <button class="login-submit" type="submit"><fmt:message key="booking.book.button.leave"/></button>
        </form>
    </div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
<%--prsonAmount--%>
<%--apartment--%>
<%--arrivalDate--%>
<%--leavingDate--%>
