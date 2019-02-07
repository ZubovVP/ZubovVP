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
    <title>Create</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="POST" id="myform"></form>
<table align="left" width="50%" border="1">
    <tr>
        <td>Name</td>
        <td>Login</td>
        <td>Email</td>
    </tr>
    <td> name : <input type="text" name="name" placeholder="Your name" form="myform"></td>
    <td> login : <input type="text" name="login" placeholder="Your login" form="myform"></td>
    <td> email : <input type="text" name="email" placeholder="Your email" form="myform"></td>
</table>
<br><br><br>
<input type="submit" name="create" value="Create" form="myform"/>
</body>
</html>