<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.11.2020
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="ru" />
<%--<fmt:setLocale value="${param.lang}" />--%>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/bookingStyles.css">
</head>
<body class="booking">
<jsp:include page="templates/header.jsp"/>
<div id="toBeFound">
    <jsp:include page="templates/leftMenu.jsp"/>
        <form method="post" class="bookingDiv" action="${pageContext.request.contextPath}/controller?command=booking">
            <h2 class="alignCenter"><fmt:message key="booking.book.room"/></h2>
            <p class="alignCenter"><fmt:message key="booking.book.select.people"/></p>
            <label class="floatingRadio firstLabel">
                <input name="personAmount" type="radio">
                1
            </label>
            <label class="floatingRadio">
                <input name="personAmount" type="radio">
                2
            </label>
            <label class="floatingRadio">
                <input name="personAmount" type="radio">
                3
            </label>
            <label class="floatingRadio">
                <input name="personAmount" type="radio">
                4
            </label>
            <label class="floatingRadio lastLabel">
                <input name="personAmount" type="radio">
                5
            </label>
            <div>
                <br>
                <br>
            </div>
            <label class="inputBookingStyle" for="cars"><fmt:message key="booking.book.room.type"/>
                <select class="apartmentType" id="cars" name="apartment">
                    <option value="standard"><fmt:message key="booking.book.room.type.op1"/></option>
                    <option value="business"><fmt:message key="booking.book.room.type.op2"/></option>
                    <option value="skyWalker"><fmt:message key="booking.book.room.type.op3"/></option>
                    <option value="lux"><fmt:message key="booking.book.room.type.op4"/></option>
                </select>
            </label>
            <div>
                <br>
                <br>
            </div>
            <label class="inputBookingStyle" for="arrivalDate"><fmt:message key="booking.book.date.arrival"/>
                <input class="dateInputArr" type="datetime-local" id="arrivalDate" name="arrivalDate">
            </label>
            <div>
                <br>
                <br>
            </div>
            <label class="inputBookingStyle" for="leavingDate"><fmt:message key="booking.book.date.leave"/>
                <input class="dateInputLeave" type="datetime-local" id="leavingDate" name="leavingDate">
            </label>
            <div>
                <br>
                <br>
            </div>
            <button class="loginSubmit" type="submit"><fmt:message key="booking.book.button.leave"/></button>
        </form>
    </div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
