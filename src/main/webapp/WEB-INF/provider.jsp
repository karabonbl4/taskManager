<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Providers</title>
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
                </div>
                <a href = "/createProvider?departmentId=${department.id}" class="btn btn-outline-success">Create new provider</a><br>
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
                                            <td><form:input type="hidden" path="email" value="${provider.email}"></form:input><a href="mailto:${provider.email}">${provider.email}</a></td>
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
            <jsp:include page="departmentDetails.jsp"/>
          <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>