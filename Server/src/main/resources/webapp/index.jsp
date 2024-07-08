<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weather Application</title>
</head>
<body>
<h1>Weather Application</h1>
<form action="WeatherServlet" method="post">
    Location: <input type="text" name="location"><br>
    <input type="submit" value="Get Weather">
</form>
</body>
</html>