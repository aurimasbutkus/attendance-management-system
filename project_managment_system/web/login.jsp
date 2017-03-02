<%--
  Created by IntelliJ IDEA.
  User: Marius
  Date: 2017-03-02
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <link rel="stylesheet" type="text/css" href="login_style.css">
    <div class="login-page">
        <div class="form">
            <form id="login" class="login-form" style = "display: block">
                <input type="text" placeholder="username"/>
                <input type="password" placeholder="password"/>
                <button onclick="location.href='index.jsp'" type="button">login</button>
                <p class="message">Not registered? <a href="#">Create an account</a></p>
            </form>
        </div>
    </div>
</html>

