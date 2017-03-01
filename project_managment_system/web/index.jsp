<%--
  Created by IntelliJ IDEA.
  User: Marius
  Date: 2017-03-01
  Time: 19:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body data-id = "home">
<style>
  h1 {
    font-family: Robo mono, Monospace;
    padding: 10px;
    font-size: 26px;
  }
  .tree-list{
    font-size: 20px;
    font-family: Monospace;
    display: block;
    padding: 7px 10px 7px 0;
    color: #696969;
    line-height: 15px;
    font-weight: 500;
    text-decoration: none;
    cursor: pointer;
  }
  .section-style {
    position: absolute;
    top: 150px;
    bottom: 0;
    left: 0;
    z-index: 1;
    overflow: hidden;
    width: 216px;
    background: #D3FFCE;
    font-size: 12px;
  }
  .panel_main {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    height: 150px;
    z-index: 2;
    background: #D3FFCE;
  }
  .logged {
    position: absolute;
    font-family: Robo mono, Monospace;
    padding: 10px;
    font-size: 26px;
    right: 0;
    top: 0;
  }
  h3{
    vertical-align: text-top;
  }
</style>
<div>
  <section class="section-style">
    <div>
      <h1>Settings:</h1>
      <ul>
        <li><a href="#" class = "tree-list">Home</a></li>
        <li><a href="#" class = "tree-list">Projects</a></li>
        <li><a href="#" class = "tree-list">Team</a></li>
        <li><a href="#" class = "tree-list">Options</a></li>
        <li><a href="#" class = "tree-list">Log Out</a></li>
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