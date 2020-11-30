<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale"/>

<ul class="leftMenuUl">
    <li><a class="leftMenuLink" href="${pageContext.request.contextPath}/controller?command=home"><fmt:message key="left.user.home"/></a></li>
    <li><a class="leftMenuLink" href="${pageContext.request.contextPath}/controller?command=profile.history"><fmt:message key="left.user.orders"/></a></li>
    <c:if test="${true}">
        <li><a class="leftMenuLink" href="${pageContext.request.contextPath}/controller?command=all.users"><fmt:message key="left.admin.users"/></a></li>
    </c:if>
    <c:if test="${param.user == 'admin'}">
        <li><a class="leftMenuLink" href="#news"><fmt:message key="left.admin.users.applications"/></a></li>
    </c:if>
</ul>
