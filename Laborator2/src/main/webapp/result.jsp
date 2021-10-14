<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Laborator 2</title>
</head>
<body>
<h1>Total results: ${totalRecords}</h1>
<br/>
<table>
    <tr>
        <th>Category</th>
        <th>Key</th>
        <th>Value</th>
    </tr>
    <c:forEach items="${records}" var="record">
        <tr>
            <td>${record.category.name}</td>
            <td>${record.key}</td>
            <td>${record.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>