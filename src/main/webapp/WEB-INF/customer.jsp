<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customers</title>
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
                <a href = "/createCustomer?departmentId=${department.id}" class="btn btn-outline-success">Create new customer</a><br>
                <c:if test = "${customers.size() == 0}">
                <h4>Customers not found</h4>
                </c:if>
                    <c:if test = "${customers.size() > 0}">
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
                                <c:forEach items="${customers}" var="customer">
                                    <tr>
                                        <td scope="raw">${customers.indexOf(customer) + 1}</td>
                                        <form:form action="/editCustomer" method="GET" modelAttribute="editCustomer">
                                            <form:input type="hidden" path="id" value="${customer.id}"></form:input>
                                            <td><form:input type="hidden" path="name" value="${customer.name}"></form:input>${customer.name}</td>
                                            <td><form:input type="hidden" path="taxNumber" value="${customer.taxNumber}"></form:input>${customer.taxNumber}</td>
                                            <td><form:input type="hidden" path="location" value="${customer.location}"></form:input>${customer.location}</td>
                                            <td><form:input type="hidden" path="owner" value="${customer.owner}"></form:input>${customer.owner}</td>
                                            <td><form:input type="hidden" path="email" value="${customer.email}"></form:input><a href="mailto:${customer.email}">${customer.email}</a></td>
                                            <form:input type="hidden" path="departmentId" value="${customer.departmentId}"></form:input>
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