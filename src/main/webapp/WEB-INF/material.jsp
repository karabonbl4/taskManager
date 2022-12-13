<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Materials</title>
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
                <a href = "/createMaterial?departmentId=${department.id}" class="btn btn-outline-success">Create new material</a><br>
                <c:if test = "${materials.size() == 0}">
                        <h4>Materials not found</h4>
                </c:if>
                    <c:if test = "${materials.size() > 0}">
                        <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Property</th>
                            <th scope="col">Value</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${materials}" var="material">
                            <tr>
                              <td scope="raw">${materials.indexOf(material) + 1}</td>
                              <form:form action="/editMaterial" method="GET" modelAttribute="editMaterial">
                                  <form:input type="hidden" path="id" value="${material.id}"></form:input>
                                  <td><form:input type="hidden" path="name" value="${material.name}"></form:input>${material.name}</td>
                                  <td><form:input type="hidden" path="property" value="${material.property}"></form:input>${material.property}</td>
                                  <td><form:input type="hidden" path="value" value="${material.value}"></form:input>${material.value}</td>
                                  <form:input type="hidden" path="departmentId" value="${material.departmentId}"></form:input>
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