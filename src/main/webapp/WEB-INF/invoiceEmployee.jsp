<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Invite</title>
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
                <div>
                    <form:form method="GET" action="/task" modelAttribute="workDayWithDepartmentIdDto">
                        <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                        <input type="button" class="w-100 btn btn-primary btn-lg" value="Tasks"></input>
                    </form:form>
                </div><br>
                <form:form method="POST" modelAttribute="newEmployee">
                    <h2 class="display-6">Invite new employee</h2>
                        <div class="mb-3 row">
                            <label for="jobTitle" class="col-sm-2 col-form-label">Job title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="jobTitle" id="jobTitle" name="jobTitle"></form:input>
                                <form:errors path="jobTitle"></form:errors>
                                ${employeeError}
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="email" class="col-sm-2 col-form-label">E-mail</label>
                                <div class="col-sm-10">
                                    <form:input type="email" class="form-control" path="email" id="email" name="email" placeholder="email@example.com"></form:input>
                                    <form:errors path="email"></form:errors>
                                    ${emailError}
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-10">
                                <form:input type="hidden"  path="departmentId" value="${department.id}"></form:input>
                            </div>
                        </div>
                    <button type="submit" class="btn btn-primary">Invite</button>
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
        </div>
    </div>
</body>
</html>