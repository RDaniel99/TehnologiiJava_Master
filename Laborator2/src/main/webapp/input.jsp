<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Laborator 2</title>
</head>
<body>
<h1><%= "Insert new record:" %></h1>
<br/>
<form action="get-input">
    Category:
    <select name="category">
        <c:forEach items="${listCategory}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
    <br/><br/>
    Key: <input id="key" type="text" name="key"/>
    <br/><br/>
    Value: <input id="value" type="text" name="value"/>
    <br/><br/>
    <!--<a href="hello-servlet?mock=javascript:document.getElementById('mock').value&value=javascript:document.getElementById('value').value&key=javascript:document.getElementById('key').value&sync=javascript:document.getElementById('sync').value">-->
    <input type="submit" value="Submit">
    <!--</a>-->
</form>
</body>
</html>