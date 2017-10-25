<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product Edit</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6 col-lg-offset-3">
                            <h3 class="text-success">Product Edit</h3>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form class="form-horizontal" action="/admin/product/update/${product.id}" method="POST"
                                  role="form">
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="title">Title:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="title" id="title" tabindex="1" class="form-control"
                                               placeholder="Title" value="${product.title}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="manufacturer">Manufacturer:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="manufacturer" id="manufacturer" tabindex="2"
                                               class="form-control"
                                               placeholder="Manufacturer" value="${product.manufacturer}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="description">Description:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="description" id="description" tabindex="3"
                                               class="form-control"
                                               placeholder="Description" value="${product.description}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="cost">Cost:</label>
                                    <div class="col-sm-10">
                                        <input type="number" name="cost" id="cost" tabindex="4"
                                               class="form-control" placeholder="Cost" value="${product.cost}">
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" tabindex="5"
                                                   class="form-control btn-success" value="Submit">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <a class="btn-block btn btn-primary" href="/index" type="button"
                                               tabindex="6">Cancel</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
