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
        <th>Password</th>
        <c:if test="${sessionScope.role == 'admin'}">
            <th>Role</th>
        </c:if>
    </tr>
    <form action="${pageContext.servletContext.contextPath}/edit" method="POST" id="myform"></form>
    <td> name : <input type="text" name="name" value="<c:out value="${param.userName}"></c:out>" form="myform"></td>
    <td> login : <input type="text" name="login" value="<c:out value="${param.login}"></c:out>" form="myform"></td>
    <td> email : <input type="text" name="email" value="<c:out value="${param.email}"></c:out>" form="myform"></td>
    <td> password : <input type="password" name="password" value="<c:out value="${param.password}"></c:out>"
                           form="myform"></td>
    <c:if test="${sessionScope.role == 'admin'}">
        <td><select name="role" form="myform">
            <option value="viewer">viewer</option>
            <option value="admin">admin</option>
        </select></td>
    </c:if>
    <c:if test="${sessionScope.role != 'admin'}">
        <input type="hidden" name="role" value="<c:out value="${param.role}"></c:out>" form="myform">
    </c:if>
    <input type="hidden" name="id" value="<c:out value="${param.id}"></c:out>" form="myform">
</table>
<br><br><br><br>
<input type="submit" name="Update" value="Update" form="myform">
</html>