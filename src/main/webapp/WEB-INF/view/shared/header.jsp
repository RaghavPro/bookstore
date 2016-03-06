<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 27/03/15
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${browser_title}</title>

    <link href="${request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${request.contextPath}/static/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="${request.contextPath}/static/css/custom.css" rel="stylesheet">

</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">Book Hive</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="<c:url value="/books"/>">Home</a>
                </li>
                <li class="">
                    <a href="<c:url value="/"/>">About Us</a>
                </li>
            </ul>
            <form action="<c:url value="/search"/>" id="search-form" class="navbar-form navbar-left" method="GET">
                <div class="form-group">
                    <input class="form-control" id="search" name="search" type="text" value="">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>

            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <div class="container">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="<c:url value="/account"/>">Account</a>
                        </li>
                        <li>
                            <a href="<c:url value="/logout"/>">Logout</a>
                        </li>
                        <li>
                            <a href="<c:url value="/cart"/>" class="glyphicon glyphicon-shopping-cart">Cart ${sessionScope.totalItems}</a>
                        </li>

                    </ul>
                    </div>
                </c:when>
                <c:when test="${sessionScope.user == null}">
                    <form action="<c:url value="/login"/>" class="navbar-form navbar-right" id="nav-login" method="POST">
                        <div class="form-group">
                            <label class="sr-only" for="username">Username</label>
                            <input type="text" class="form-control" name="username" id="username" placeholder="username">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="password">Password</label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="********">
                        </div>
                        <button type="submit" class="btn btn-success">Sign in</button>
                        <a class="btn btn-danger" style="margin-right:15px" href="<c:url value="/register"/>" role="button">Register</a>
                    </form>
                </c:when>
            </c:choose>
        </div>
    </nav>
    <div class="jumbotron">
        <div class="container">
            <h1>Hello, there!</h1>

            <h2>Welcome to BookHive</h2>

            <p>"Whenever you read a good book, somewhere in the world a door opens to allow in more light."</p>
            <p>-Vera Nazarian</p>

            <p><a class="btn btn-primary btn-lg">Buy now</a></p>
        </div>
    </div>