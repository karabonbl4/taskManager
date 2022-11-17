<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${department.name}</title>
</head>
<body>
    <div class="container">
        <c:if test="${department.authUserFunction=='manager'}">
            <jsp:include page="common/header.jsp"/>
        </c:if>
        <c:if test="${department.authUserFunction!='manager'}">
            <jsp:include page="index.jsp"/>
        </c:if>
          <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <div>
                    <button type="button" onclick="document.location='/task?department_id=${department.id}&calendar='" class="w-100 btn btn-primary btn-lg">Tasks</button>
                </div>
            </div>
            <div class="col-md-5 col-lg-4 order-md-last">
                <div class="card text-bg-info mb-3" style="max-width: 18rem;">
                  <div class="card-header">Info</div>
                  <div class="card-body">
                    <h5 class="card-title">Department info</h5>
                    <p class="card-text">Title: ${department.name}</p>
                    <p class="card-text">Location: ${department.location}</p>
                    <h5 class="card-title">User info</h5>
                    <p class="card-text">Username: ${pageContext.request.userPrincipal.name}</p>
                    <p class="card-text">Function: ${department.authUserFunction}</p>
                  </div>
                </div>
            </div>
          </div>
    </div>
</body>
</html>