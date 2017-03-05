<%--
  Created by IntelliJ IDEA.
  User: Marius
  Date: 2017-03-02
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<html>
<head>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="login_style.css">
</head>
<body>
    <div class="login-page">
        <div class="form">
            <form action = "register" method="post" class="register-form" style = "display: block">
                <input name="name" type="text" placeholder="name"/>
                <input name="pass" type="password" placeholder="password"/>
                <input name="email" type="text" placeholder="email address"/>
                <input type="submit" value="create">
                <p class="message">Already registered? <a href="login.jsp">Sign In</a></p>
            </form>
        </div>
    </div>
</body>>
</html>
