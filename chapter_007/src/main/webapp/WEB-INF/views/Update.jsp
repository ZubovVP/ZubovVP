<%--
  Created by Intellij IDEA.
  User: Vitaly Zubov
  Email: Zubov.VP@yandex.ru
  Version: $Id$
  Date: 05.02.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <form action="<%=request.getContextPath()%>/edit" method="POST" id="myform"></form>
    <td> name : <label>
        <input type="text" name="name" value="<%=request.getParameter("userName")%>" form="myform">
    </label></td>
    <td> login : <label>
        <input type="text" name="login" value="<%=request.getParameter("login")%>" form="myform">
    </label></td>
    <td> email : <label>
        <input type="text" name="email" value="<%=request.getParameter("email")%>" form="myform">
    </label></td>
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>" form="myform">
</table>
<br><br><br>
<input type="submit" name="Update" value="Update" form="myform">
</html>