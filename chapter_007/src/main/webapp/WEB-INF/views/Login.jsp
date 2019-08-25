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
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: "Times New Roman", serif;
        background-color: #459DE5;
    }

    .box {
        width: 300px;
        padding: 27px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background: #ffccff;
        border-radius: 50px;
    }

    .box input[type = "text"], .box input[type = "password"] {
        background: none;
        display: block;
        margin: 20px auto;
        text-align: center;
        border: 2px solid #ffbf80;
        padding: 14px 10px;
        width: 200px;
        outline: none;
        color: white;
        border-radius: 24px;
    }

    .box input[type = "submit"] {
        background: none;
        display: block;
        margin: 20px auto;
        text-align: center;
        border: 2px solid #00802b;
        padding: 14px 40px;
        width: 200px;
        outline: none;
        color: white;
        border-radius: 24px;
    }

    .box h2 {
        text-align: center;
    }

    .box h3 {
        text-align: center;
    }

    .navbar-brand img {
        width: 40px;
    }
</style>
    <script>
    function validate() {
        var result = true;
        if ($('#name').val() === '') {
            result = false;
            alert('Please, write your name');
        }
        if ($('#password').val() === '') {
            result = false;
            alert('Please, write your password');
        }
        return result;
    }
</script>
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
            <img src="<c:url value="http://www.seanritter.com/assets/js-logo-d20abe9ebbeb5be6242dbf06660f22a115e2dbdaf9659276198cce9dbe7f69e4.png"/>"
                 class="float-left">
        </a>

        <a class="navbar-brand" href="https://www.java.com/">
            <img src="<c:url value="https://fiverr-res.cloudinary.com/images/t_main1,q_auto,f_auto/gigs/1284113/original/b39d9f8fd959132ec77dcd14a4df26b9620b34ba/code-java-program-fix-errors-and-do-your-assignments-and-projects.png"/>"
                 class="float-left">
        </a>
    </div>
</nav>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <h3><c:out value="${error}"/></h3>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" class="box" method="POST" onsubmit="return validate();">
    <h1> Authentication </h1>
    <br>
    <h2>Login</h2> <input type="text" id = name name="login">
    <h2>Password</h2> <input type="password" id = password name="password"><br>
    <input type="submit" value="Login" align="center">
</form>
</body>
</html>
