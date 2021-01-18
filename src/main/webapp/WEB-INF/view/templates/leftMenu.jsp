<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<ul class="left-menu-ui">
    <c:choose>
        <c:when test="${sessionScope.role == 'ADMIN'}">
            <li >
                <a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=admin_create_room">
                    <fmt:message key="left.admin.room.add"/>
                </a>
            </li>
            <li>
                <a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=admin_users">
                    <fmt:message key="left.admin.users"/>
                </a>
            </li>
            <li>
                <a class="left-menu-link"
                   href="${pageContext.request.contextPath}/controller?command=admin_active_applications">
                    <fmt:message key="left.admin.users.applications"/>
                </a>
            </li>
            <li>
                <a class="left-menu-link"
                   href="${pageContext.request.contextPath}/controller?command=admin_all_applications">
                    <fmt:message key="left.admin.all.applications"/>
                </a>
            </li>
            <li>
                <a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=admin_rooms">
                    <fmt:message key="left.admin.rooms"/>
                </a>
            </li>
        </c:when>
        <c:otherwise>
            <li>
                <a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=booking">
                    <fmt:message key="header.authorized.booking"/>
                </a>
            </li>
            <li>
                <a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=profileHistory">
                    <fmt:message key="left.user.orders"/>
                </a>
            </li>
            <li>
                <a class="left-menu-link" href="${pageContext.request.contextPath}/controller?command=user_bills">
                    <fmt:message key="left.user.bills"/>
                </a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>
