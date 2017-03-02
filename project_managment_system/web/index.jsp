<%--
  Created by IntelliJ IDEA.
  User: Marius
  Date: 2017-03-01
  Time: 19:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body data-id = "home">
<div>
  <link rel="stylesheet" type="text/css" href="index.css">
  <section class="section-style">
    <div>
      <h1>Settings:</h1>
      <ul>
        <li><a href="#" class = "tree-list">Home</a></li>
        <li><a href="#" class = "tree-list">Projects</a></li>
        <li><a href="#" class = "tree-list">Team</a></li>
        <li><a href="#" class = "tree-list">Options</a></li>
        <li><a href="login.jsp" class = "tree-list">Log Out</a></li>
      </ul>
    </div>
  </section>
  <main class = "panel_main">
    <a href="#"><img width = 160px alt="Logo placeholder" src="http://bit.ly/2m9tkad"></a>
    <div class= "logged">
      <label>Logged in as: ________</label>
    </div>
  </main>
</div>
</body>
</html>