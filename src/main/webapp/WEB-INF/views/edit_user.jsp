<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4>
        <b>Edit user <c:out value="${user.username}"/></b>
    </h4>
    <form action="<c:url value="/admin/user/update/${user.id}"/>" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="username"
                   value="<c:out value="${user.username}"/>">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="password"
                   value="<c:out value="${user.password}"/>">
        </div>
        <div class="form-group">
            <c:forEach items="${roles}" var="role">
                <label>
                    <input type="radio" name="role" value="<c:out value="${role}"/>" required
                           <c:if test="${role eq user.role}">checked</c:if>/>
                    <c:out value="${role}"/>
                </label>
                &nbsp;&nbsp;
            </c:forEach>
        </div>
        <div class="form-group">
            <label>
                <input type="radio" name="locked" value="true" required
                       <c:if test="${user.locked}">checked</c:if>/> Locked
            </label>
            &nbsp;&nbsp;
            <label>
                <input type="radio" name="locked" value="false" required
                       <c:if test="${!user.locked}">checked</c:if>/> Not Locked
            </label>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Update">
        </div>
    </form>
</div>
</body>
</html>
