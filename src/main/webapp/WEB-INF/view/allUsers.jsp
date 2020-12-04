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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/profileStyles.css">
</head>
<body class="body">
<jsp:include page="templates/header.jsp"/>
<div id="to-be-found">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="table-wrapper">
        <table id="customers">
            <tr>
                <th>#</th>
                <th><fmt:message key="admin.user.login"/></th>
                <th><fmt:message key="admin.user.status"/></th>
                <th><fmt:message key="admin.user.role"/></th>
                <th><fmt:message key="admin.user.status.change"/></th>
            </tr>
            <c:forEach var="user" items="${users}" varStatus="index">
                <tr>
                    <td>${(7)*(page - 1) + index.count}</td>
                    <td>${user.login}</td>
                    <td>${user.isDisabled}</td>
                    <td>User</td>
                    <td>
                        <c:if test="${user.isDisabled}">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/controller?command=updateUser"
                                  class="admin-users-form">
                                <input type="hidden" value="${user.login}" name="login">
                                <input type="hidden" value="${user.userId}" name="userId">
                                <input type="hidden" value="${user.password}" name="pass">
                                <input type="hidden" value="${user.isDisabled}" name="isDisabled">
                                <button type="submit"><fmt:message key="admin.user.status.enable"/></button>
                            </form>
                        </c:if>
                        <c:if test="${!user.isDisabled}">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/controller?command=updateUser"
                                  class="admin-users-form">
                                <input type="hidden" value="${user.login}" name="login">
                                <input type="hidden" value="${user.userId}" name="userId">
                                <input type="hidden" value="${user.password}" name="pass">
                                <input type="hidden" value="${user.isDisabled}" name="isDisabled">
                                <button type="submit"><fmt:message key="admin.user.status.disable"/></button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:choose>
            <c:when test="${(page - 1) == 0}">
                <a href="" type="submit" class="pagination-children">❮</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/controller?command=all.users&page=${page-1}" type="submit"
                   name="+" class="pagination-children">❮</a>
            </c:otherwise>
        </c:choose>
        <div class="pagination-children">${page}</div>
        <c:choose>
            <c:when test="${users.size() != 7}">
                <a href="" type="submit" class="pagination-children">❯</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/controller?command=all.users&page=${page+1}" type="submit"
                   name="+" class="pagination-children">❯</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
