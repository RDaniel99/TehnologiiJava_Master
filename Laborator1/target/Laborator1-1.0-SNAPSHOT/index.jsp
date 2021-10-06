<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1><%= "Hello World!" %></h1>
    <br/>
    <form action="hello-servlet">
        Mock (true/false): <input id="mock" type="text" name="mock"/>
        <br/><br/>
        Sync (true/false): <input id="sync" type="text" name="sync"/>
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