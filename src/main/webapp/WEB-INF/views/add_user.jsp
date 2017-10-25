<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add a new user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Add a new user</b></h4>
    <form action="<c:url value="/user/add"/>" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="username" required autofocus placeholder="Username">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" required placeholder="Password">
        </div>
        <c:if test="${is_admin}">
            <div class="form-group">
                <c:forEach items="${roles}" var="role">
                    <label>
                        <input type="radio" name="role" value="<c:out value="${role}"/>" required/>
                        <c:out value="${role}"/>
                    </label>
                    &nbsp;&nbsp;
                </c:forEach>
            </div>
            <div class="form-group">
                <label>
                    <input type="radio" name="locked" value="true" required/>Locked
                </label>
                &nbsp;&nbsp;
                <label>
                    <input type="radio" name="locked" value="false" checked required/>Not locked
                </label>
            </div>
        </c:if>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Add">
        </div>
    </form>
</div>
</body>
</html>
