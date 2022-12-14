<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit department</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form action="/editDepartment" method="POST" modelAttribute="editDepartment">
                    <h2 class="display-6">Edit ${editDepartment.name}</h2>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-2 col-form-label">Title</label>
                            <div class="col-auto">
                                <form:input type="text" class="form-control" path="name" id="name" value="${editCustomer.name}"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="location" class="col-sm-2 col-form-label">Location</label>
                                <div class="col-auto">
                                    <form:input type="text" class="form-control" id="location" path="location" name="location" value="${editDepartment.location}"></form:input>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-auto">
                                <form:input type="hidden" path="id" value="${editDepartment.id}"></form:input>
                                <form:input type="hidden" path="manager" value="${editCustomer.manager}"></form:input>
                                <form:input type="hidden" path="authUserFunction" value="${editDepartment.authUserFunction}"></form:input>
                            </div>
                            <form:errors path="id"></form:errors><div class="error">${departmentError}</div>
                        </div>
                    <button type="submit" class="btn btn-primary">Edit</button>
                    <a href="/department" class="btn btn-link">Back to departments</a>
                </form:form>
            </div>
            <jsp:include page="departmentDetails.jsp"/>
         <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>