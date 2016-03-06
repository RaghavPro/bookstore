<%--
  Created by IntelliJ IDEA.
  User: RaghavFTW
  Date: 03/07/15
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/view/shared/header.jsp" />



<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="pull-right" style="margin-top:10px;">
        <div class="col-md-10 col-md-offset-1 pull-right">
          <img class="img-error" src="/static/images/bookworm.gif">
          <h2>404 Not Found</h2>
          <p>Sorry, the page you're looking for does not exist.</p>
          <div class="error-actions">
            <a href="/books" class="btn btn-danger btn-lg">
              <span class="glyphicon glyphicon-arrow-left"></span>
              Back Home
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>




<jsp:include page="/WEB-INF/view/shared/footer.jsp" />