<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Materials</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <h1 class="display-6">Add material to task</h1>
                <c:if test = "${materials.size() == 0}">
                        <h4>Materials not found</h4>
                </c:if>
                    <form:form action="/createTask?departmentId=${department.id}" method="GET" modelAttribute="newTask">
                        <form:input type="hidden" path="id" value="${newTask.id}"></form:input>
                        <form:input type="hidden" path="name" value="${newTask.name}"></form:input>
                        <form:input type="hidden" path="description" value="${newTask.description}"></form:input>
                        <form:input type="hidden" path="deadlineDate" value="${deadLineDate}"></form:input>
                        <form:input type="hidden" path="deadlineTime" value="${deadLineTime}"></form:input>
                        <fmt:formatDate value="${newTask.workday}" pattern="yyyy-MM-dd" var="workDay"/>
                        <form:input type="hidden" path="workday" value="${workDay}"></form:input>
                        <form:input type="hidden" path="priority" value="${newTask.priority}"></form:input>
                        <form:input type="hidden" path="departmentId" value="${newTask.departmentId}"></form:input>
                        <form:input type="hidden" path="condition" value="${newTask.condition}"></form:input>
                        <form:input type="hidden" path="executors" value="${newTask.executors}"></form:input>
                    <c:if test = "${materials.size() > 0}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Property</th>
                                    <th scope="col">Value</th>
                                    <th scope="col">Count</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${materials}" var="material">
                                    <tr>
                                        <td scope="raw">${materials.indexOf(material) + 1}</td>
                                        <td>${material.name}</td>
                                        <td>${material.property}</td>
                                        <td>${material.value}</td>
                                        <form:input type="hidden" path="tempMaterials" value="${material.id}"></form:input>
                                        <td><form:input type="text" path="tempMaterials" value="0"></form:input></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div><button type="submit" class="btn btn-outline-success" name="departmentId" value="${department.id}">Add material</button></div>
                    </c:if>
                    </form:form>
            </div>
            <jsp:include page="departmentDetails.jsp"/>
          <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>