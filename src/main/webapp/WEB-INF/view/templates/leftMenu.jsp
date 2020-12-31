<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<ul class="left-menu-ui">
    <li><a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=home"><fmt:message key="left.user.home"/></a></li>
    <li><a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=profileHistory"><fmt:message key="left.user.orders"/></a></li>
    <c:if test="${sessionScope.role == 'ADMIN'}">
        <li><a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=admin_users"><fmt:message key="left.admin.users"/></a></li>
        <li><a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=admin_applications"><fmt:message key="left.admin.users.applications"/></a></li>
        <li><a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=admin_rooms"><fmt:message key="left.admin.rooms"/></a></li>
        <li><a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=admin_create_room"><fmt:message key="left.admin.room.add"/></a></li>
    </c:if>
</ul>
