<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
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
                <a href = "invoiceEmployee?departmentId=${department.id}">Invite an employee</a><br>
                <c:if test = "${employees.size() == 0}">
                        <h4>Employees not found</h4>
                </c:if>
                    <c:if test = "${employees.size() > 0}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Username</th>
                                    <th scope="col">Function</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${employees}" var="employee">
                                <tr>
                                    <td scope="raw">${employees.indexOf(employee) + 1}</td>
                                    <form:form action="/editEmployee" method="GET" modelAttribute="editEmployee">
                                    <td><form:input type="hidden" path="username" value="${employee.username}"></form:input>${employee.username}</td>
                                    <td><form:input type="hidden" path="jobTitle" value="${employee.jobTitle}"></form:input>${employee.jobTitle}</td>
                                    <form:input type="hidden" path="id" value="${employee.id}"></form:input>
                                    <form:input type="hidden" path="email" value="${employee.email}"></form:input>
                                    <form:input type="hidden" path="departmentId" value="${employee.departmentId}"></form:input>
                                    <c:if test="${employee.jobTitle!='manager'}">
                                    <td><input type="submit" name="edit" class="btn btn-secondary btn-sm" value="Edit"></td>
                                    <td><input type="submit" name="delete" class="btn btn-danger btn-sm" value="Delete"/></td>
                                    </c:if>
                                    <c:if test="${employee.jobTitle=='manager'}">
                                    <td></td>
                                    <td></td>
                                    </c:if>
                                    </form:form>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
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