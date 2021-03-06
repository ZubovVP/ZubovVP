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
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            if ($('#name').val() === '') {
                result = false;
                alert('Please, fill your name');
            }
            if ($('#login').val() === '') {
                result = false;
                alert('Please, fill your login');
            }
            if ($('#email').val() === '') {
                result = false;
                alert('Please, fill email');
            }
            if ($('#password').val() === '') {
                result = false;
                alert('Please, fill password');
            }
            if ($('#country').val() === '') {
                result = false;
                alert('Please, fill country');
            }
            if ($('#city').val() === '') {
                result = false;
                alert('Please, fill city');
            }
            if (result) {
                sendPerson();
            }
            return result;
        }

        function sendPerson() {
            var name = $('#name').val();
            var login = $('#login').val();
            var email = $('#email').val();
            var password = $('#password').val();
            var role = $('#role').val();
            var id = $('#id').val();
            var country = $('#country').val();
            var city = $('#city').val();


            $.ajax({
                type: 'POST',
                url: "${pageContext.servletContext.contextPath}/edit",
                data: {name: name, login: login, email: email, password: password, role: role, id: id, country: country, city: city},
                dataType: 'application/json',
                success: function (data) {
                    console.log(JSON.parse(data.responseText));
                }
            });
        }
    </script>
</head>
<body>
<style type="text/css">
    body {
        margin: 0;
        background-color: orange;
    }

    .display-4 {
        text-align: center;
    }

    .navbar-brand img {
        width: 40px;
    }

    .button {
        width: 150px;
        height: 40px;
        border-radius: 20px;
        background: #459DE5;
        color: #fff;
        font-size: 18px;
        cursor: pointer;
        position: relative;
        left: 42%;
        transform: translate(-42%, 0);
    }

    table {
        border: black;
        background-color: white;
        margin: auto;
        width: 60%;
    }

    .header_table {
        color: black;
        font-weight: bold;
        text-align: center;
    }
</style>
<nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #99ffff;">
    <div class="container-fluid">
        <a class="navbar-brand" href="http://www.bootstrap.com">
            <img src="<c:url value="https://www.drupal.org/files/project-images/bootstrap-stack.png"/>"
                 class="float-left">

        </a>
        <a class="navbar-brand" href="https://html.com">
            <img src="<c:url value="https://cdn-images-1.medium.com/max/1200/1*MJ9Y4_tCTv99Gs_xZYlKrA.png"/>"
                 class="float-left">
        </a>
        <a class="navbar-brand" href="https://www.w3.org/Style/CSS/">
            <img src="<c:url value="http://codingwithalex.com/wp-content/uploads/2017/09/CSS-1200x1200.png"/>"
                 class="float-left">
        </a>

        <a class="navbar-brand" href="https://www.javascript.com">
            <img src="<c:url value="https://fiverr-res.cloudinary.com/images/t_main1,q_auto,f_auto/gigs/1284113/original/b39d9f8fd959132ec77dcd14a4df26b9620b34ba/code-java-program-fix-errors-and-do-your-assignments-and-projects.png"/>"
                 class="float-left">
        </a>

        <a class="navbar-brand" href="https://www.java.com/">
            <img src="<c:url value="https://cdn.icon-icons.com/icons2/1381/PNG/512/java_93883.png"/>"
                 class="float-left">
        </a>
        <form class="form-inline" action="${pageContext.servletContext.contextPath}/">
            <button class="btn btn-success" type="submit">Home</button>
        </form>
    </div>
</nav>
<div class="display-4">
    Correct user
</div>
<br>
<table border="1">
    <tr class="header_table">
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Password</th>
        <th>Country</th>
        <th>City</th>
        <c:if test="${sessionScope.role == 'admin'}">
            <th>Role</th>
        </c:if>
    </tr>
    <form action="${pageContext.servletContext.contextPath}/" method="GET" onsubmit="return validate();">
        <td> name : <input type="text" id="name" name="name" value="<c:out value="${param.userName}"></c:out>"></td>
        <td> login : <input type="text" id="login" name="login" value="<c:out value="${param.login}"></c:out>"></td>
        <td> email : <input type="text" id="email" name="email" value="<c:out value="${param.email}"></c:out>"></td>
        <td> password : <input type="password" id="password" name="password"
                               value="<c:out value="${param.password}"></c:out>"></td>
        <td> country : <input type="text" id="country" name="country" value="<c:out value="${param.country}"></c:out>"></td>
        <td> city : <input type="text" id="city" name="city" value="<c:out value="${param.city}"></c:out>"></td>
        <c:if test="${sessionScope.role == 'admin'}">
        <td><select name="role" id="role">
            <option value="viewer">viewer</option>
            <option value="admin">admin</option>
        </select></td>
        </c:if>
        <c:if test="${sessionScope.role != 'admin'}">
        <input type="hidden" name="role" value="<c:out value="${param.role}"></c:out>">
        </c:if>
        <input type="hidden" name="id" id="id" value="<c:out value="${param.id}"></c:out>">
</table>
<br>
<input type="submit" value="Cancel" class="button" style="background: #cc0000;" form="cancel">
<input type="submit" class="button" value="Update">
</form>
<form action="${pageContext.servletContext.contextPath}/" method="GET" id="cancel">
</form>
</body>
</html>