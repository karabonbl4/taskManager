<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit provider</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form action="/editProvider" method="POST" modelAttribute="editProvider">
                    <h2 class="display-6">Edit ${editProvider.name}</h2>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-2 col-form-label">Title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="name" id="name" value="${editProvider.name}"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="taxNumber" class="col-sm-2 col-form-label">Tax number</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <form:input type="text" class="form-control" path="taxNumber" id="taxNumber" value="${editProvider.taxNumber}"></form:input>
                                    </div>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="location" class="col-sm-2 col-form-label">Location</label>
                                <div class="col-sm-10">
                                    <form:input type="text" class="form-control" id="location" path="location" name="location" value="${editProvider.location}"></form:input>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="owner" class="col-sm-2 col-form-label">Owner</label>
                            <div class="col-sm-10">
                            <form:input type="text" class="form-control" id="owner" path="owner" value="${editProvider.owner}"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="email" class="col-sm-2 col-form-label">E-mail</label>
                            <div class="col-sm-10">
                                <form:input type="email" class="form-control" id="email" path="email" value="${editProvider.email}"></form:input>
                                <form:errors path="email"></form:errors> <div class="error text-center">${providerError}</div>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-10">
                                <form:input type="hidden" path="departmentId" value="${editProvider.departmentId}"></form:input>
                                <form:input type="hidden" path="id" value="${editProvider.id}"></form:input>
                            </div>
                        </div>
                    <button type="submit" class="btn btn-primary">Edit</button>
                    <a href="/provider?departmentId=${editProvider.departmentId}" class="btn btn-link">Back to providers</a>
                </form:form>
            </div>
            <jsp:include page="departmentDetails.jsp"/>
         <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>