<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 04/03/15
  Time: 01:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../shared/header.jsp"/>


<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
            <div id="myCarousel" class="carousel slide">
                <ol class="carousel-indicators">
                    <c:forEach begin="0" end="${noOfCovers - 1}" var="i">
                    <li data-target="#myCarousel" data-slide-to="${i}"
                    <c:if test="${i == 0}">
                        class="active"
                    </c:if>
                    >
                        </c:forEach>

                </ol>
                <div class="carousel-inner">
                    <c:forEach begin="0" end="${noOfCovers - 1}" var="i">
                        <div class="<c:if test="${i == 0}">active</c:if> item">
                            <img class="img-rounded productImage" alt="Book cover"
                                 src="/static/images/covers/${book.isbn}-${i+1}.jpg">
                        </div>
                    </c:forEach>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span
                        class="icon-prev"></span></a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next"><span
                        class="icon-next"></span></a>
            </div>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-1">
        </div>

        <div class="col-xs-12 col-sm-12 col-md-8 col-lg-7">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <h1 class="lead">${book.title}</h1>
                    <h4 class="lead">
                        <s>&#8377;${book.mrp}</s>
                    </h4>

                    <p>

                    <h3 class="lead">&#8377;${book.price}</h3></p>
                    <br/><br/>
                    <a class="btn btn-warning" href="<c:url value="/addcart?isbn=${book.isbn}"/>" role="button">Buy
                        now</a>
                    <a class="btn btn-danger" role="button">Add to wishlist</a>

                    <p class="lead">${book.summary}</p>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                    <div class="row">
                        <div class="col-xs-12 col-sm-6 col-md-4">
                            <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <c:if test="${book.aboutAuthor != null}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">About Author</h3>
                    </div>
                    <div class="panel-body">
                            ${book.aboutAuthor}
                    </div>
                </div>
            </c:if>
            <c:if test="${book.review != null}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Review</h3>
                    </div>
                    <div class="panel-body">
                            ${book.review}
                    </div>
                </div>
            </c:if>
        </div>

        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Book details</h3>
                </div>
                <table class="table table-striped table-hover ">
                    <tbody>
                    <tr>
                        <td>Papaerback</td>
                        <td class="details_table">${book.pages}</td>
                    </tr>
                    <tr>
                        <td>Publisher</td>
                        <td class="details_table">${book.publisher}</td>
                    </tr>
                    <tr>
                        <td>Language</td>
                        <td class="details_table">${book.language}</td>
                    </tr>
                    <tr>
                        <td>ISBN-10</td>
                        <td class="details_table">${book.isbn}</td>
                    </tr>
                    <tr>
                        <td>Dimensions</td>
                        <td class="details_table">${book.dimensions}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <hr/>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="reviews">
            <br/>
            <p class="lead">User Reviews</p>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <div class="col-lg-12" style="padding: 0;">
                        <div class="well well-sm">
                            <form accept-charset="UTF-8" id="review-form" action="<c:url value="/addreview"/>"
                                  method="post">
                                <input id="isbn" name="isbn" type="hidden" value="${book.isbn}">

                                <div class="form-group">

                                    <input class="form-control" type="text" id="title" name="title"
                                           placeholder="Enter title">
                                </div>
                                <div class="form-group">
                                    <textarea style="max-width: 100%" class="form-control" cols="50"
                                              name="review" placeholder="Enter your review here..." rows="5"></textarea>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Rating</label>

                                    <select class="form-control" id="stars" name="stars">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                                <div class="text-right">
                                    <button type="reset" class="btn btn-default">Cancel</button>
                                    <button class="btn btn-success btn" type="submit">Send</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="lead"><a href="<c:url value="/login"/>">Login</a> to give review</p>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${book.userReviews}" var="review">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding: 0;">
                    <blockquote style="border: 2px solid #eeeeee;">
                        <h4>
                            <c:forEach begin="0" end="${review.stars}">
                                <span class="glyphicon glyphicon-star"></span>
                            </c:forEach>
                            <c:forEach begin="1" end="${5 - review.stars}">
                                <span class="glyphicon glyphicon-star-empty"></span>
                            </c:forEach>
                                ${book.title}
                        </h4>
                        <br/>

                        <p class="lead">${review.review}</p>
                        <small>
                                ${review.reviewer} on ${review.reviewDate}
                        </small>
                    </blockquote>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="../shared/footer.jsp"/>
