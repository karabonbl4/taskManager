<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit material</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form action="/editMaterial" method="POST" modelAttribute="editMaterial">
                    <h2 class="display-6">Edit ${editMaterial.name}</h2>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-2 col-form-label">Title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="name" id="name" value="${editMaterial.name}"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="property" class="col-sm-2 col-form-label">Property</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <form:input type="text" class="form-control" path="property" id="property" value="${editMaterial.property}"></form:input>
                                        <form:errors path="property"></form:errors> ${materialError}
                                    </div>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="value" class="col-sm-2 col-form-label">Value</label>
                                <div class="col-sm-10">
                                    <form:input type="text" class="form-control" id="value" path="value" value="${editMaterial.value}"></form:input>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <form:input type="hidden" path="id" value="${editMaterial.id}"></form:input>
                            <form:input type="hidden" path="departmentId" value="${editMaterial.departmentId}"></form:input>
                        </div>
                    <button type="submit" class="btn btn-primary">Edit</button>
                    <a href="/material?departmentId=${department.id}" class="btn btn-link">Back to materials</a>
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