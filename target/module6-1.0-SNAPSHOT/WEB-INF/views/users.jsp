<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Users</b></h4>
    <c:if test="${is_admin}">
        <a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
           href="<c:out value="/user/new"/>">Add New</a>
        <a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
           href="<c:out value="/admin/user/delete/all"/>">Delete All</a>
    </c:if>
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>Role</th>
                <c:if test="${is_admin}">
                    <th>Edit</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <c:if test="${is_admin}">
                        <td>
                            <a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
                               href="<c:out value="/admin/user/edit/${user.id}"/>">Edit</a>
                        </td>
                        <td>
                            <a class="btn btn-xs btn-danger active" role="button" style="margin: 5px"
                               href="<c:out value="/admin/user/delete/${user.id}"/>">Delete</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
