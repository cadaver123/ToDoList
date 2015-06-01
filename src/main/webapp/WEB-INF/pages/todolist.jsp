<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <style>
    body {
        background: white }
    section {
        background: black;
        color: white;
        border-radius: 1em;
        padding: 1em;
        position: absolute;
        top: 30%;
        left: 50%;
        margin-right: -50%;
        transform: translate(-50%, -50%) }
  </style>
<title>ToDo page</title>
</head>
<body>
<section>
<fieldset>
<legend>Todos</legend>
<form action="remove" method="POST" accept-charset="UTF-8">
<c:forEach items="${list}" var="item">
    <input type="radio" name="task" value="${item}">${item}<br>
</c:forEach>
<br>
<input type="submit" value="remove">

</form><br>
<form action="add" method="POST" accept-charset="UTF-8">
<input type="text" name="task" ><input type="submit" value="Submit">
</form>
</fieldset>
</section>
</body>
</html>