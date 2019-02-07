<%--
 Created by Intellij IDEA.
 User: Vitaly Zubov
 Email: Zubov.VP@yandex.ru
 Version: $Id$
 Date: 30.12.2018
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User_view</title>
</head>
<body>
<%@ page import="ru.job4j.models.User" %>
<%@ page import="java.util.List" %>
<table width="100%" border="0">
    <th align="left">Id</th>
    <th align="left">Name</th>
    <th align="left">Login</th>
    <th align="left">Email</th>
    <th align="left">Create time</th>

    <%for (User user : (List<User>)request.getAttribute("users")) { %>
    <tr>
        <td align="left"><%= user.getId()%>
        </td>
        <td align="left"><%= user.getName()%>
        </td>
        <td align="left"><%= user.getLogin()%>
        </td>
        <td align="left"><%= user.getEmail()%>
        </td>
        <td align="left"><%= user.getCreateDate()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="GET">
                <input type="submit" name="submit" value="Correct" style="float: right">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="hidden" name="userName" value="<%=user.getName()%>">
                <input type="hidden" name="login" value="<%=user.getLogin()%>">
                <input type="hidden" name="email" value="<%=user.getEmail()%>">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/delete" method="POST">
                <input type="submit" name="submit" value="Delete" style="float: left">
                <input type="hidden" name="id" value="<%=user.getId()%>">
            </form>
        </td>
    </tr>
    <%}%>
</table>
<td>
    <form action="<%=request.getContextPath()%>/find" method="POST">
        <label for="sub"></label><input type="text" id="sub" name="id" value="0">
        <input type="submit" name="submit" value="Find by id">
    </form>
</td>
<td>
    <form action="<%=request.getContextPath()%>/create" method="GET">
        <input type="submit" name="submit" value="Create new user">
    </form>
</td>
</body>
</html>
