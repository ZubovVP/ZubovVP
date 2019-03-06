<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 18.02.2019
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<strong> Authentication </strong>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="POST">
Login : <input type="text" name="login"><br/>
Password : <input type="password" name="password"><br/>
    <input type = "submit">
</form>
</body>
</html>
