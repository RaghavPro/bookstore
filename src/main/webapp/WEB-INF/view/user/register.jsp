<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 04/03/15
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../shared/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <div class="well bs-component">
                <form action="/register" id="registration-form" class="form-horizontal" method="post">
                    <fieldset>
                        <legend>Register</legend>

                        <c:choose>
                            <c:when test="${created == true}">
                                <div class="alert alert-dismissible alert-success">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Well done!</strong> You have successfully registered. You can
                                    <a href="/login" class="alert-link">login</a> now.
                                </div>
                            </c:when>
                            <c:when test="${created == false}">
                                <div class="alert alert-dismissible alert-danger">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Uh-oh!</strong> Something unusual happened.
                                    Please <a href="/register" class="alert-link">register</a> again.
                                </div>
                            </c:when>
                            <c:when test="${already_exist == true}">
                                <div class="alert alert-dismissible alert-danger">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    <strong>Uh-oh!</strong> This username is already taken.
                                    Try a different username.
                                </div>
                            </c:when>
                        </c:choose>
                        <div class="form-group">
                            <label for="inputFirstName" class="col-lg-3 control-label">First name</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="inputFirstName" name="inputFirstName"
                                       placeholder="First name">
                                <span class="" for="inputFirstName" id="spanFirstName" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputLastName" class="col-lg-3 control-label">Last name</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="inputLastName" name="inputLastName"
                                       placeholder="Last name">
                                <span class="" for="inputLastName" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputUsername" class="col-lg-3 control-label">Username</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="inputUsername" name="inputUsername"
                                       placeholder="Username">
                                <span class="" for="inputUsername" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword" class="col-lg-3 control-label">Password</label>

                            <div class="col-lg-6">
                                <input type="password" class="form-control" name="inputPassword" id="inputPassword"
                                       placeholder="Password">
                                <span class="" for="inputPassword" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputconfirmPassword" class="col-lg-3 control-label">Password</label>

                            <div class="col-lg-6">
                                <input type="password" class="form-control" name="inputconfirmPassword"
                                       id="inputconfirmPassword"
                                       placeholder="Password">
                                <span class="" for="inputconfirmPassword" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputEmail" class="col-lg-3 control-label">Email</label>

                            <div class="col-lg-7">
                                <input type="text" class="form-control" id="inputEmail" name="inputEmail"
                                       placeholder="Email">
                                <span class="" for="inputEmail" aria-hidden="true"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address" class="col-lg-3 control-label">Address</label>

                            <div class="col-lg-9">
                            <textarea style="max-width: 100%" class="form-control" rows="3" id="address"
                                      name="address"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Gender</label>

                            <div class="col-lg-6">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="gender" id="male" value="Male"
                                               checked="">
                                        Male
                                    </label>
                                    <br>
                                    <label>
                                        <input type="radio" name="gender" id="female" value="female">
                                        Female
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-3 col-lg-4">
                                <button type="reset" class="btn btn-default">Cancel</button>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../shared/footer.jsp"/>

