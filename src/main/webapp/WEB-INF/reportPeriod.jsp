<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Report</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <c:set var="calendar" value=""/>
    <div class="container">
        <div class="row g-3">
            <div class="col-md-7 col-lg-8">
                <h1 class="display-6">Select period to view the tasks</h1>
                <form:form method="GET" action="/reportPeriod" class="row" modelAttribute="period">
                    <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                    <div class="col"><a href="/report?departmentId=${department.id}&page=1" class="btn btn-primary">Today</a></div>
                    <div class="col-auto"><a href="/reportWeek?departmentId=${department.id}&page=1" class="btn btn-primary">Last week</a></div>
                    <div class="col"><p class="text-center">From:</p></div>
                    <div class="col"><form:input type="date" id="fromDate" class="form-control" path="fromDate"></form:input></div>
                    <div class="col"><p class="text-center">To:</p></div>
                    <div class="col"><form:input type="date" id="toDate" class="form-control" path="toDate"></form:input></div>
                    <div class="col"><button type="submit" class="btn btn-primary">Show</button></div>
                    <div class="col"><form:errors path="fromDate"></form:errors><div class="error text-center">${dateError}</div>
                    <form:input type="hidden" path="page" value="${period.page}"></form:input></div></div></form:form>
                    <div class="col-md-5 col-lg-4 order-md-last"><nav aria-label="...">
                      <ul class="pagination justify-content-end">
                      <c:forEach var="counter" begin="1" end="${countPage}">
                      <fmt:formatDate value="${period.fromDate}" pattern="yyyy-MM-dd" var="fromDate"/>
                      <fmt:formatDate value="${period.toDate}" pattern="yyyy-MM-dd" var="toDate"/>
                        <li class="page-item"><a class="page-link" href="/reportPeriod?departmentId=${department.id}&fromDate=${fromDate}&toDate=${toDate}&page=${counter}">${counter}</a></li>
                      </c:forEach>
                      </ul>
                    </nav></div>
            </div>
        <div>
        <c:if test = "${tasks.size() == 0}">
            <h4>Tasks not found</h4>
        </c:if>
    </div>
    <div>
        <c:if test = "${tasks.size() > 0}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Description</th>
                        <th scope="col">Materials</th>
                        <th scope="col">Priority</th>
                        <th scope="col">Executors</th>
                        <th scope="col">Deadline</th>
                        <th scope="col">Condition</th>
                        <th scope="col">Workday</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tasks}" var="task">
                        <tr>
                            <td scope="raw">${tasks.indexOf(task) + 1 + 5*(page - 1)}</td>
                            <td>${task.name}</td>
                            <td>${task.description}</td>
                            <td><c:if test="${empty task.tempMaterials}"><button type="button" class="btn btn-outline-secondary btn-sm" data-bs-container="body" data-bs-toggle="popover" data-bs-placement="bottom" data-bs-content="${task.tempMaterials}" disabled>
                                Materials</button></c:if>
                                <c:if test="${!empty task.tempMaterials}"><button type="button" class="btn btn-outline-secondary btn-sm" data-bs-container="body" data-bs-toggle="popover" data-bs-placement="bottom" data-bs-content="${task.tempMaterials}">
                                Materials</button></c:if>
                                </td>
                            <td>${task.priority}</td>
                            <td><c:forEach items="${task.employees}" var="executor">
                                <p>${executor.name}</p></c:forEach></td>
                            <fmt:formatDate value="${task.deadLine}" pattern="yyyy-MM-dd HH:mm:ss" var="deadline"/>
                            <td>${deadline}</td>
                            <td>
                                <c:if test="${task.condition=='IN_PROCESS'}">
                                    <button type="button" class="btn btn-info btn-sm" disabled>In process</button></c:if>
                                <c:if test="${task.condition=='CONFIRMED'}">
                                    <button type="button" class="btn btn-secondary btn-sm" disabled>Confirmed</button></c:if>
                                <c:if test="${task.condition=='DONE'}">
                                    <button type="button" class="btn btn-success btn-sm"disabled>Done</button></c:if>
                                <c:if test="${task.condition=='FAILED'}">
                                    <button type="button" class="btn btn-danger btn-sm" disabled>Failed</button></c:if>
                            </td>
                            <fmt:formatDate value="${task.workday}" pattern="yyyy-MM-dd" var="workday"/>
                            <td><p>${workday}</p></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script> const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
        const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))</script>
      <jsp:include page="common/footer.jsp"/>
</body>
</html>