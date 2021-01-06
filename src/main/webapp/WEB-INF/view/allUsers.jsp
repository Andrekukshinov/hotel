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
                <th class="small-font"><fmt:message key="admin.user.login"/></th>
                <th><fmt:message key="admin.user.status"/></th>
                <th><fmt:message key="admin.user.role"/></th>
                <th><fmt:message key="admin.user.status.change"/></th>
            </tr>
<%--            todo think of resizing table--%>
            <c:forEach var="user" items="${users}" varStatus="index">
                <tr>
                    <td>${(itemsPerPage)*(page - 1) + index.count}</td>
                    <td class="small-font">${user.login}</td>
                    <td class="small-font"><fmt:message key="admin.user.status.state.${user.isDisabled}"/></td>
                    <td class="small-font small-letters">
                        <fmt:message key="admin.user.role.${user.role}"/>
                    </td>
                    <td>
                        <form method="post"
                              action="${pageContext.request.contextPath}/controller?command=admin_update_user"
                              class="admin-users-form">
                            <input type="hidden" value="${user.login}" name="login">
                            <input type="hidden" value="${user.userId}" name="userId">
                            <input type="hidden" value="${user.password}" name="pass">
                            <input type="hidden" value="${user.role}" name="role">
                            <input type="hidden" value="${user.isDisabled}" name="isDisabled">
                            <button class="small-font" type="submit"><fmt:message key="admin.user.status.${user.isDisabled}"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="pages">
            <c:choose>
                <c:when test="${(page - 1) == 0}">
                    <a href="" type="submit" class="pagination-children">❮</a>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_users&page=1"
                       type="submit"
                       class="pagination-children active">1</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_users&page=${page-1}"
                       type="submit"
                       name="+" class="pagination-children">❮</a>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_users&page=1"
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
                    <a href="${pageContext.request.contextPath}/controller?command=admin_users&page=${lastPage}"
                       type="submit"
                       class="pagination-children active">${lastPage}</a>
                    <a href="" type="submit" class="pagination-children">❯</a>
                </c:when>
                <c:otherwise>
                    <div class="pagination-children">...</div>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_users&page=${lastPage}"
                       type="submit"
                       class="pagination-children ">${lastPage}</a>
                    <a href="${pageContext.request.contextPath}/controller?command=admin_users&page=${page+1}"
                       type="submit"
                       class="pagination-children">❯</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="templates/footer.jsp"/>

</body>
</html>
