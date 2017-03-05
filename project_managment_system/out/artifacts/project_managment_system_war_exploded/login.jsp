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
            <form action = "login" method="post" class="login-form" style = "display: block">
                <input name="uname"type="text" placeholder="username"/>
                <input name="upass" type="password" placeholder="password"/>
                <input type="submit" value="login">
                <p class="message">Not registered? <a href="register.jsp">Create an account</a></p>
            </form>
        </div>
    </div>
</body>>
</html>
