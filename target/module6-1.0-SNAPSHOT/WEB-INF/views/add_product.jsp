<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add a new product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Add a new product</b></h4>
    <form action="/admin/product/add" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="title" required placeholder="Title">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="manufacturer" required placeholder="Manufacturer">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="description" required placeholder="Description">
        </div>
        <div class="form-group">
            <input type="number" class="form-control" name="cost" required placeholder="Cost">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Add">
        </div>
    </form>
</div>
</body>
</html>