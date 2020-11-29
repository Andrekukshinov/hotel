<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.11.2020
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/commonStyles.css">

</head>
<body class="body">
<jsp:include page="templates/header.jsp"/>
<div id="toBeFound">
    <jsp:include page="templates/leftMenu.jsp"/>
    <p>welcome ${login}</p>
<%--    todo implem--%>
</div>
<jsp:include page="templates/footer.jsp"/>

</body>
</html>
