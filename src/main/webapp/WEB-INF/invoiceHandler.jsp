<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Handle of invoice</title>
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
                        <input type="submit" class="w-100 btn btn-primary btn-lg" value="Tasks"></input>
                    </form:form>
                    </div><br>
                <form:form method="POST" modelAttribute="newEmployee">
                      <div class="card border-primary mb-3 text-center" style="max-width: 18rem;">
                            <div class="card-header">Invoice</div>
                                <div class"card-body">
                                <h5 class="card-title">${department.name}, ${department.location}</h5>
                                   <div class="mb-3 row">
                                       <label for="jobTitle" class="col-sm-6 col-form-label">Job title</label>
                                      <div class="col-sm-6">
                                        <form:input type="text" readonly="true" class="form-control-plaintext" path="jobTitle" id="jobTitle" value="${newEmployee.jobTitle}" />
                                      </div>
                                   </div>
                                  <div class="mb-3 row">
                                       <label for="username" class="col-sm-6 col-form-label">Username</label>
                                  <div class="col-sm-6">
                                       <form:input type="text" readonly="true" class="form-control-plaintext" path="username" id="username" value="${username}" />
                                  </div>
                                  </div>
                                    <div>
                                        <div>
                                            <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                                        </div>
                                    </div>
                                </div>
                      </div>
                    <input type="submit" class="btn btn-primary" name="submit" value="Agree"/>
                    <input type="submit" class="btn btn-danger" name="cancel" value="Disagree"/>
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