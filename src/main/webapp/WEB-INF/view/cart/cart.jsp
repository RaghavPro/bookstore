<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 05/03/15
  Time: 06:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../shared/header.jsp"/>

<div class="container">
    <div class="col-xs-12 col-md-12 col-lg-12 col-sm-12 content">
        <div class="row">
            <div class="col-md-12">
                <ol class="breadcrumb">
                    <li><a href="<c:url value="/books"/>">Books</a></li>
                    <li class="active">Cart</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-primary panel-shadow">
                    <div class="panel-heading">
                        <h3>
                            <img class="img-circle img-thumbnail" style="width:150px; height: 150px"
                                 src="/static/images/users/${sessionScope.user.userId}.jpg">
                            ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Description</th>
                                    <th>Qty</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${cartItems}" var="cartItem" varStatus="loop">
                                    <tr>
                                        <td>
                                            <a href="/details?isbn=${cartItem.book.isbn}">
                                                <img src="/static/images/covers/${cartItem.book.isbn}-1.jpg"
                                                     class="img-cart">
                                            </a>
                                        </td>
                                        <td><strong>Product ${loop.index + 1}</strong>

                                            <p>${cartItem.book.title}</p>

                                            <p>${cartItem.book.author}</p>
                                        </td>
                                        <td>
                                            <form id="cart-form" class="form-inline" action="<c:url value="/editcart"/>">
                                                <label for="quantity"></label>
                                                <input class="form-control" id="quantity" name="quantity" type="text"
                                                       value="${cartItem.quantity}">
                                                <input type="hidden" name="isbn" value="${cartItem.book.isbn}">
                                                <button rel="tooltip" class="btn btn-default"><i
                                                        class="glyphicon glyphicon-pencil"></i></button>
                                                <a href="<c:url value="/removecart?isbn=${cartItem.book.isbn}"/>"
                                                   class="btn btn-danger">
                                                    <i class="glyphicon glyphicon-trash"></i>
                                                </a>
                                            </form>
                                        </td>
                                        <c:set value="${cartItem.book.price}" var="price"/>
                                        <c:set value="${price * cartItem.quantity}" var="totalItemPrice"/>
                                        <td>&#8377;${price}</td>
                                        <td>&#8377;${totalItemPrice}</td>
                                    </tr>
                                </c:forEach>


                                <tr>
                                    <td colspan="6">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="text-right">Total Product</td>
                                    <td>${totalPrice}</td>
                                </tr>
                                <c:set value="${totalItems * 20}" var="shipping"/>
                                <tr>
                                    <td colspan="4" class="text-right">Total Shipping</td>
                                    <td>${shipping}</td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="text-right"><strong>Total</strong></td>
                                    <td>${shipping + totalPrice}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <a href="<c:url value="/books"/>" class="btn btn-success"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Continue
                    Shopping</a>

                <a href="<c:url value="/order"/>" class="btn btn-primary pull-right">Order<span
                        class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../shared/footer.jsp"/>
