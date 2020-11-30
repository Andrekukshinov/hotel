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
<div id="toBeFound">
    <jsp:include page="templates/leftMenu.jsp"/>
    <div class="tableWrapper">
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
                            <button><fmt:message key="admin.user.status.enable"/></button>
                        </c:if>
                        <c:if test="${!user.isDisabled}">
                            <button><fmt:message key="admin.user.status.disable"/></button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form method="post"
              action="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}"
              class="pagination">
            <c:choose>
                <c:when test="${(page - 1) == 0}">
                    <button type="submit" disabled class="paginationChildren">❮</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="paginationChildren">❮</button>
                </c:otherwise>
            </c:choose>
            <input type="hidden" name="page" value="${page}"/>
            <div class="paginationChildren">${page}</div>
            <c:choose>
                <c:when test="${users.size() != 7}">
                    <button type="submit" disabled name="+" class="paginationChildren">❯</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" name="+" class="paginationChildren">❯</button>
                </c:otherwise>
            </c:choose>

        </form>


    </div>

</div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
