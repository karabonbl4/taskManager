<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New material</title>
</head>
<body>
    <c:if test="${department.authUserFunction=='manager'}">
        <jsp:include page="common/header.jsp"/>
    </c:if>
    <c:if test="${department.authUserFunction!='manager'}">
        <jsp:include page="index.jsp"/>
    </c:if>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form action="/createMaterial?departmentId=${department.id}" method="POST" modelAttribute="newMaterial">
                    <h2 class="display-6">Create new material</h2>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-2 col-form-label">Title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="name" id="name"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="property" class="col-sm-2 col-form-label">Property</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <form:input type="text" class="form-control" path="property" id="property" rows="2"></form:input>
                                        <form:errors path="property"></form:errors> ${materialError}
                                    </div>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="value" class="col-sm-2 col-form-label">Value</label>
                                <div class="col-sm-10">
                                    <form:input type="text" class="form-control" id="value" path="value"></form:input>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-10">
                                <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                            </div>
                        </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                    <a href="/material?departmentId=${editMaterial.departmentId}" class="btn btn-link">Back to materials</a>
                </form:form>
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
          <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>