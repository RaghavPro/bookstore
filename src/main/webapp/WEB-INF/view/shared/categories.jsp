<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-xs-12 col-sm-4 col-md-3">
    <p class="lead">All Categories</p>
    <div class="list-group">
        <c:forEach var="category" items="${categories}" varStatus="loop">
            <c:choose>
                <c:when test="${requestScope.categoryId != 0 && loop.index == 0}">
                    <a class="list-group-item" href="?page=1&sortBy=${sortBy}&search=${search}&category=${category.parentId}">
                        &laquo; ${category.categoryName}
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="list-group-item" href="?page=1&sortBy=${sortBy}&search=${search}&category=${category.categoryId}">
                            ${category.categoryName}
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>


