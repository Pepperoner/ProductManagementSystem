<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Login</b></h4>
    <c:if test="${param.error ne null}">
        <div class="text-danger" role="alert">
            <h5>Error</h5>
        </div>
    </c:if>
    <c:if test="${param.logout ne null}">
        <div class="text-success" role="alert">
            <h5>You have successfully logged out.</h5>
        </div>
    </c:if>
    <form action="<c:url value='/login'/>" method='post'>
        <div class="form-group">
            <input id="username" class="form-control" type="text" name="username" required autofocus
                   placeholder="Username">
        </div>
        <div class="form-group">
            <input id="password" class="form-control" type="password" name="password" required
                   placeholder="Password">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Submit">
            &nbsp;&nbsp;
            <a href="<c:url value='/user/new'/>">
                <input type="button" class="btn btn-primary" value="Registration">
            </a>
        </div>
    </form>
</div>
</body>
</html>