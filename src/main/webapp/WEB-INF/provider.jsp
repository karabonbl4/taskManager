<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Providers</title>
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
                <a href = "/createProvider?departmentId=${department.id}">Create new provider</a><br>
                <c:if test = "${providers.size() == 0}">
                <h4>Providers not found</h4>
                </c:if>
                    <c:if test = "${providers.size() > 0}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Tax number</th>
                                    <th scope="col">Location</th>
                                    <th scope="col">Owner</th>
                                    <th scope="col">E-mail</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${providers}" var="provider">
                                    <tr>
                                        <td scope="raw">${providers.indexOf(provider) + 1}</td>
                                        <form:form action="/editProvider" method="GET" modelAttribute="editProvider">
                                        <form:input type="hidden" path="id" value="${provider.id}"></form:input>
                                            <td><form:input type="hidden" path="name" value="${provider.name}"></form:input>${provider.name}</td>
                                            <td><form:input type="hidden" path="taxNumber" value="${provider.taxNumber}"></form:input>${provider.taxNumber}</td>
                                            <td><form:input type="hidden" path="location" value="${provider.location}"></form:input>${provider.location}</td>
                                            <td><form:input type="hidden" path="owner" value="${provider.owner}"></form:input>${provider.owner}</td>
                                            <td><form:input type="hidden" path="email" value="${provider.email}"></form:input>${provider.email}</td>
                                            <form:input type="hidden" path="departmentId" value="${provider.departmentId}"></form:input>
                                            <td><input type="submit" name="edit" class="btn btn-secondary btn-sm" value="Edit"></td>
                                            <td><input type="submit" name="delete" class="btn btn-danger btn-sm" value="Delete"/></td>
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