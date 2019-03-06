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
    <title>Create</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="POST" id="myform"></form>
<table align="left" width="75%" border="1">
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
        <td>Password</td>
    </tr>
    <td> name : <input type="text" name="name" placeholder="Your name" form="myform"></td>
    <td> login : <input type="text" name="login" placeholder="Your login" form="myform"></td>
    <td> email : <input type="text" name="email" placeholder="Your email" form="myform"></td>
    <td> password : <input type="password" name="password" form="myform"></td>
</table>
<br><br><br>
    <select name="role" form="myform">
        <option value="viewer">viewer</option>
        <option value="admin">admin</option>
    </select>
</form>
<input type="submit" name="create" value="Create" form="myform"/>
</body>
</html>