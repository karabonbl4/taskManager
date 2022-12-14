<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form action="/editEmployee" method="POST" modelAttribute="editEmployee">
                    <h2 class="display-6">Edit ${editEmployee.username}</h2>
                        <div class="mb-3 row">
                            <label for="username" class="col-sm-2 col-form-label">Username</label>
                            <div class="col-auto">
                                <form:input type="text" readonly="true" class="form-control" path="username" id="username" value="${editEmployee.username}"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="jobTitle" class="col-sm-2 col-form-label">Job title</label>
                                <div class="col-auto">
                                    <form:input type="text" class="form-control" id="jobTitle" path="jobTitle" value="${editEmployee.jobTitle}"></form:input>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-auto">
                                <form:input type="hidden" path="id" value="${editEmployee.id}"></form:input>
                                <form:input type="hidden" path="email" value="${editEmployee.email}"></form:input>
                                <form:input type="hidden" path="departmentId" value="${editEmployee.departmentId}"></form:input>
                            </div>
                            <form:errors path="id"></form:errors><div class="error text-center">${employeeError}</div>
                        </div>
                    <button type="submit" class="btn btn-primary">Edit</button>
                    <a href="/employee?departmentId=${editEmployee.departmentId}" class="btn btn-link">Back to employees</a>
                </form:form>
            </div>
            <jsp:include page="departmentDetails.jsp"/>
         <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>