<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.11.2020
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<c:choose>
    <c:when test="${sessionScope.login != null}">
        <c:redirect url="/controller?command=home"/>
    </c:when>
    <c:otherwise>
        <jsp:forward page="WEB-INF/view/login.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
