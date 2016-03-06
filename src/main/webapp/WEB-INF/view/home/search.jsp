<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 02/03/15
  Time: 04:17
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom_functions.tld" prefix="fn" %>

<jsp:include page="../shared/header.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="../shared/categories.jsp"/>
        <div class="col-xs-12 col-sm-8 col-md-9">
            <div class="pull-right btn-group">
                <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    Sort by
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="<c:url value="/search?page=1&sortBy=${Constants.RELEVANCE}&search=${requestScope.search}&category=${categoryId}"/>">${Constants.RELEVANCE}</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="<c:url value="/search?page=1&sortBy=${Constants.BESTSELLERS}&search=${requestScope.search}&category=${categoryId}"/>">${Constants.BESTSELLERS}</a>
                    </li>
                    <li>
                        <a href="<c:url value="/search?page=1&sortBy=${Constants.NEW}&search=${requestScope.search}&category=${categoryId}"/>">${Constants.NEW}</a>
                    </li>
                    <li>
                        <a href="<c:url value="/search?page=1&sortBy=${Constants.LOW_TO_HIGH}&search=${requestScope.search}&category=${categoryId}"/>">${Constants.LOW_TO_HIGH}</a>
                    </li>
                    <li>
                        <a href="<c:url value="/search?page=1&sortBy=${Constants.HIGH_TO_LOW}&search=${requestScope.search}&category=${categoryId}"/>">${Constants.HIGH_TO_LOW}</a>
                    </li>
                </ul>
            </div>
            <p class="lead">${requestScope.sortBy} - <i>"${requestScope.search}"</i></p>

            <c:choose>
                <c:when test="${requestScope.books != null}">
                    <jsp:include page="../shared/books.jsp"/>
                </c:when>
                <c:otherwise>
                    <div class="not_found lead">
                        Nothing was found. Unfortunately.
                    </div>
                </c:otherwise>
            </c:choose>


            <%-- DISPLAY BOOKS --%>


            <c:if test="${requestScope.books != null}">

                <%-- PAGINATION --%>
                <div class="text-center">
                        <%--PREVIOUS BUTTON --%>
                    <ul class="pagination">
                        <c:set var="has_previous" value="${pagination.hasPrevious}"/>
                        <li <c:if test="${not has_previous}"> class="disabled" </c:if>>
                            <a <c:if
                                    test="${has_previous}"> href="/search?page=${pagination.currentPage - 1}&sortBy=${sortBy}&search=${search}&category=${categoryId}"</c:if>
                                    aria-label="Previous">
                                <span aria-hidden="true">&laquo; Prev</span>
                            </a>
                        </li>
                    </ul>

                        <%-- FIRST PAGE --%>
                    <c:set value="${pagination.displayPages}" var="displayPages"/>
                    <c:if test="${not fn:contains(displayPages, 1) }">
                        <ul class="pagination">
                            <li>
                                <a href="/search?page=1&sortBy=${sortBy}&search=${search}&category=${categoryId}">1</a>
                            </li>
                        </ul>
                    </c:if>

                        <%-- PAGE NUMBERS --%>
                    <ul class="pagination">
                        <c:forEach var="page_number" items="${displayPages}">
                            <li <c:if test="${page == page_number}"> class="active" </c:if>>
                                <a <c:if test="${page != page_number}">
                                    href="/search?page=${page_number}&sortBy=${sortBy}&search=${search}&category=${categoryId}"
                                </c:if>>
                                        ${page_number}</a>
                            </li>
                        </c:forEach>
                    </ul>

                        <%-- LAST PAGE --%>
                    <c:set var="lastPage" value="${pagination.totalPages}"/>
                    <c:if test="${not fn:contains(displayPages, lastPage) }">
                        <ul class="pagination">
                            <li>
                                <a href="/search?page=${lastPage}&sortBy=${sortBy}&search=${search}&category=${categoryId}">${lastPage}</a>
                            </li>
                        </ul>
                    </c:if>

                        <%-- NEXT BUTTON --%>
                    <c:set var="has_next" value="${pagination.hasNext}"/>
                    <ul class="pagination">
                        <li <c:if test="${not has_next}"> class="disabled" </c:if>>
                            <a <c:if
                                    test="${has_next}"> href="/search?page=${pagination.currentPage + 1}&sortBy=${sortBy}&search=${search}&category=${categoryId}"</c:if>
                                    aria-label="Next">
                                <span aria-hidden="true">Next &raquo;</span>
                            </a>
                        </li>
                    </ul>
                </div>

            </c:if>

        </div>

    </div>

</div>
<jsp:include page="../shared/footer.jsp"/>
