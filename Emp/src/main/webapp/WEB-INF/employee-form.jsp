<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 5/22/2024
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>New Employee Form</h1>
<form action="insert" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="position">Position:</label><br>
    <input type="text" id="position" name="position"><br>
    <label for="salary">Salary:</label><br>
    <input type="text" id="salary" name="salary"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
