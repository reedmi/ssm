<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>SSM</title>
</head>
<body>
    list users.
    <c:forEach items="${users}" var="user" varStatus="vs">
        <p>${user.id} - ${user.name} - ${user.age} - ${user.birthDay} - <a href="/users/delete/${user.id}">删除</a></p>
    </c:forEach>
</body>
</html>
