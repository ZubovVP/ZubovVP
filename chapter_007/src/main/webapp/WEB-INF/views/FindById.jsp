<%--
  Created by Intellij IDEA.
  User: Vitaly Zubov
  Email: Zubov.VP@yandex.ru
  Version: $Id$
  Date: 05.02.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User_view</title>
</head>
<body>
<%@ page import="ru.job4j.models.User" %>
<table width="100%" border="0">
    <th align="left">Id</th>
    <th align="left">Name</th>
    <th align="left">Login</th>
    <th align="left">Email</th>
    <th align="left">Create time</th>
    <tr>
        <c:set var="user" scope="session" value="user" />
        <td align="left"> <c:out value="${user.id}"></c:out></td>
        <td align="left"> <c:out value="${user.name}"></c:out></td>
        <td align="left"> <c:out value="${user.login}"></c:out></td>
        <td align="left"> <c:out value="${user.email}"></c:out></td>
        <td align="left"> <c:out value="${user.createDate}"></c:out></td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/edit" method="GET">
                <input type="submit" name="submit" value="Correct" style="float: right">
                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                <input type="hidden" name="userName" value="<c:out value="${user.name}"></c:out>">
                <input type="hidden" name="login" value="<c:out value="${user.login}"></c:out>">
                <input type="hidden" name="email" value="<c:out value="${user.createDate}"></c:out>">
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/delete" method="POST">
                <input type="submit" name="submit" value="Delete" style="float: left">
                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
            </form>
        </td>
    </tr>
    <td>
        <form action="${pageContext.servletContext.contextPath}/" method="GET">
            <input type="submit" name="submit" value="Home" style="float: left">
        </form>
    </td>
</table>
</body>
</html>