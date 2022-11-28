<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New provider</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form action="/createProvider?departmentId=${department.id}" method="POST" modelAttribute="newProvider">
                    <h2 class="display-6">Create new provider</h2>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-2 col-form-label">Title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="name" id="name" name="name"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="taxNumber" class="col-sm-2 col-form-label">Tax number</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <form:input type="text" class="form-control" path="taxNumber" id="taxNumber" rows="2"></form:input>
                                        <form:errors path="taxNumber"></form:errors> ${providerError}
                                    </div>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="location" class="col-sm-2 col-form-label">Location</label>
                                <div class="col-sm-10">
                                    <form:input type="text" class="form-control" id="location" path="location" name="location"></form:input>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="owner" class="col-sm-2 col-form-label">Owner</label>
                            <div class="col-sm-10">
                            <form:input type="text" class="form-control" id="owner" path="owner" name="owner"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="email" class="col-sm-2 col-form-label">E-mail</label>
                            <div class="col-sm-10">
                                <form:input type="email" class="form-control" id="email" path="email" name="email"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-10">
                                <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                            </div>
                        </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                    <a href="/provider?departmentId=${department.id}" class="btn btn-link">Back to provider</a>
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