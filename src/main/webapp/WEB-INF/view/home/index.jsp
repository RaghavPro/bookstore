<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 24/03/15
  Time: 03:51
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
                    <li><a href="/books?page=1&sortBy=${Constants.BESTSELLERS}&category=${categoryId}">${Constants.BESTSELLERS}</a></li>
                    <li class="divider"></li>
                    <li><a href="/books?page=1&sortBy=${Constants.NEW}&category=${categoryId}">${Constants.NEW}t</a></li>
                    <li><a href="/books?page=1&sortBy=${Constants.LOW_TO_HIGH}&category=${categoryId}">${Constants.LOW_TO_HIGH}</a></li>
                    <li><a href="/books?page=1&sortBy=${Constants.HIGH_TO_LOW}&category=${categoryId}">${Constants.HIGH_TO_LOW}</a></li>
                </ul>
            </div>
            <p class="lead">${requestScope.sortBy}</p>

            <%-- DISPLAY BOOKS --%>
            <jsp:include page="../shared/books.jsp"/>


            <%-- PAGINATION --%>
            <div class="text-center">
                <%--PREVIOUS BUTTON --%>
                <ul class="pagination">
                    <c:set var="has_previous" value="${pagination.hasPrevious}"/>
                    <li <c:if test="${not has_previous}"> class="disabled" </c:if>>
                        <a <c:if
                                test="${has_previous}"> href="/books?page=${pagination.currentPage - 1}&sortBy=${sortBy}&category=${categoryId}"</c:if>
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
                            <a href="/books?page=1&sortBy=${sortBy}&category=${categoryId}">1</a>
                        </li>
                    </ul>
                </c:if>

                <%-- PAGE NUMBERS --%>
                <ul class="pagination">
                    <c:forEach var="page_number" items="${displayPages}">
                        <li <c:if test="${page == page_number}"> class="active" </c:if>>
                            <a <c:if test="${page != page_number}">
                                href="/books?page=${page_number}&sortBy=${sortBy}&category=${categoryId}"
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
                            <a href="/books?page=${lastPage}&sortBy=${sortBy}&category=${categoryId}">${lastPage}</a>
                        </li>
                    </ul>
                </c:if>

                <%-- NEXT BUTTON --%>
                <c:set var="has_next" value="${pagination.hasNext}"/>
                <ul class="pagination">
                    <li <c:if test="${not has_next}"> class="disabled" </c:if>>
                        <a <c:if
                                test="${has_next}"> href="/books?page=${pagination.currentPage + 1}&sortBy=${sortBy}&category=${categoryId}"</c:if>
                                aria-label="Next">
                            <span aria-hidden="true">Next &raquo;</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </div>

</div>
<jsp:include page="../shared/footer.jsp"/>
