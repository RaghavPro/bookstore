<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 02/03/15
  Time: 02:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/custom_functions.tld" prefix="fn" %>
<div class="row">
  <c:forEach var="book" items="${books}">
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
      <div class="thumbnail">
        <a href="<c:url value="/details?isbn=${book.isbn}"/>" title="${book.title}">
          <div class="book_image">
            <img style="width: 174px; height: 239px;" class="img-rounded"
                 src="/static/images/covers/${book.isbn}-1.jpg">
          </div>
        </a>

        <div class="caption">
          <div class="book_title">
            <h4 class="pull-right">
              &#8377; ${book.price}
              <c:if test="${book.mrp ne 0}">
                <br><s>&#8377; <c:out value="${book.mrp}"/></s>
              </c:if>
            </h4>
            <h5 style="width: 70%;">
              <a href="<c:url value="/details?isbn=${book.isbn}"/>" title="${book.title}">${fn:trim(book.title, 40)}</a>
            </h5>
          </div>
          <h6>by <a>${fn:trim(book.author, 30)}</a></h6>
        </div>

          <%-- RATINGS & REVIEWS --%>
        <div class="ratings">
          <c:set var="no_of_reviews" value="${book.noOfReviews}"/>
          <c:choose>
            <c:when test="${no_of_reviews ne 0}">
              <a class="pull-right" href="<c:url value="/details?isbn=${book.isbn}#reviews"/>">${no_of_reviews}
                reviews</a>
            </c:when>
            <c:otherwise>
              <p class="pull-right">No reviews</p>
            </c:otherwise>
          </c:choose>

          <c:set var="average_rating" value="${book.totalStars/no_of_reviews}"/>
          <p>
            <c:forEach begin="0" end="${average_rating}" var="i">
              <span class="glyphicon glyphicon-star"></span>
            </c:forEach>
            <c:forEach begin="0" end="${5 - average_rating}" var="i">
              <span class="glyphicon glyphicon-star-empty"></span>
            </c:forEach>
          </p>
        </div>

      </div>
    </div>
  </c:forEach>
</div>
