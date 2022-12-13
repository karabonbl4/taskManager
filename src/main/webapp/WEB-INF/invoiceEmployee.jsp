<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Invite</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <div>
                    <form:form method="GET" action="/task" modelAttribute="workDayWithDepartmentIdDto">
                        <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                        <input type="submit" class="w-100 btn btn-primary btn-lg" value="Tasks"></input>
                    </form:form>
                </div><br>
                <form:form method="POST" modelAttribute="newEmployee">
                    <h2 class="display-6">Invite new employee</h2>
                        <div class="mb-3 row">
                            <label for="jobTitle" class="col-sm-2 col-form-label">Job title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="jobTitle" id="jobTitle" name="jobTitle" required="required"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="email" class="col-sm-2 col-form-label">E-mail</label>
                                <div class="col-sm-10">
                                    <form:input type="email" class="form-control" path="email" id="email" name="email" placeholder="email@example.com" required="required"></form:input>
                                    <form:errors path="email"></form:errors>
                                    <div class="error text-center">${inviteError}</div>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-10">
                                <form:input type="hidden"  path="departmentId" value="${department.id}"></form:input>
                            </div>
                        </div>
                    <button type="submit" class="btn btn-primary">Invite</button>
                    <a href="/employee?departmentId=${department.id}" class="btn btn-link">Back to employees</a>
                </form:form>
            </div>
            <jsp:include page="departmentDetails.jsp"/>
            <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>