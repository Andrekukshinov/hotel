<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.12.2020
  Time: 18:27
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
</head>
<body class="body">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="table-wrapper">
        <table id="applications">
            <tr>
                <th>#</th>
                <th class="small-font">Description</th>
                <th>ApplicationState</th>
            </tr>
            <c:forEach var="application" items="${applications}" varStatus="index">
                <tr>
                    <td>${(10)*(page - 1) + index.count}</td>

                    <td class="small-font">
                        <ul>
                            <li>person amount: ${application.personAmount}</li>
                            <li>apartment type: ${application.type}</li>
                            <li>arrival date: ${application.arrivalDate}</li>
                            <li>leaving date: ${application.leavingDate}</li>
                            <li>status: ${application.status}</li>
<%--                            <li>application author: ${application.login}</li>--%>
                        </ul>
                    </td>
                    <td>
<%--                        todo impl the logics--%>
                        <form method="post"
                              action="${pageContext.request.contextPath}/controller?command=admin_suggest_room&applicationId=${application.id}"
                              class="admin-users-form">
                            <button type="submit">suggest</button>
                        </form>
                        <br>
                        <form method="post"
                              action="${pageContext.request.contextPath}/controller?command=admin_update_application"
                              class="admin-users-form">
                            <input type="hidden" value="DENIED" name="status">
                            <input type="hidden" value="${application.userId}" name="userId">
                            <input type="hidden" value="${application.id}" name="id">
                            <button type="submit">reject</button>
                        </form>
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
                    <a href="${pageContext.request.contextPath}/controller?command=admin_applications&page=${page-1}"
                       type="submit"
                       name="+" class="pagination-children">❮</a>
                </c:otherwise>
            </c:choose>
            <div class="pagination-children">${page}</div>
            <c:choose>
                <c:when test="${applications.size() != 10}">
                    <a href="" type="submit" class="pagination-children">❯</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_applications&page=${page+1}"
                       type="submit"
                       name="+" class="pagination-children">❯</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
