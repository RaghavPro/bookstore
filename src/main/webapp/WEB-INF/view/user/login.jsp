<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 05/03/15
  Time: 01:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../shared/header.jsp"/>


<div class="container">
    <div class="row">
        <div class="col-lg-5">
            <div class="well bs-component">

                <form action="/login" id="form-login" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Login</legend>
                        <c:choose>
                            <c:when test="${requestScope.not_found == true}">
                                <div class="alert alert-dismissable alert-danger">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Wrong input!</strong> This username does not exist in our database.
                                </div>
                            </c:when>
                            <c:when test="${requestScope.wrong_pass == true}">
                                <data value="alert alert-dismissable alert-danger">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Uh-oh!</strong> The password you entered does not match.
                                </data>
                            </c:when>
                        </c:choose>

                        <div class="form-group">
                            <label class="col-lg-3 control-label" for="username">User name</label>

                            <div class="col-lg-7">
                                <input class="form-control" id="username"
                                       name="username" type="text" placeholder="Username"
                                <c:if test="${requestScope.wrong_pass == true}">
                                       value="${requestScope.username}"
                                </c:if>
                                        >

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label" for="password">Password</label>

                            <div class="col-lg-7">
                                <input class="form-control" id="password" name="password"
                                       type="password" placeholder="********">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-3 col-lg-5">
                                <div class="checkbox">
                                    <label>
                                        <input id="rememberme" name="rememberme" type="checkbox"> Remember me?
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-3 col-lg-3">
                                <input type="submit" value="Log in" class="btn btn-default">
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../shared/footer.jsp"/>
