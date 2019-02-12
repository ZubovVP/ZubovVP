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
    <title>Update</title>
</head>
<table align="left" width="60%" border="1">
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
    </tr>
    <form action="${pageContext.servletContext.contextPath}/edit" method="POST" id="myform"></form>
    <td> name : <label>

        <input type="text" name="name" value="<c:out value="${param.userName}"></c:out>" form="myform">
    </label></td>
    <td> login : <label>
        <input type="text" name="login" value="<c:out value="${param.login}"></c:out>" form="myform">
    </label></td>
    <td> email : <label>
        <input type="text" name="email" value="<c:out value="${param.email}"></c:out>" form="myform">
    </label></td>
    <input type="hidden" name="id" value="<c:out value="${param.id}"></c:out>" form="myform">
</table>
<br><br><br>
<input type="submit" name="Update" value="Update" form="myform">
</html>