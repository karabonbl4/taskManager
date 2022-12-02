<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <form:form method="POST" action="/report" class="row" modelAttribute="period">
                    <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                    <div class="col"><a href="/report?departmentId=${department.id}" class="btn btn-primary">Today</a></div>
                    <div class="col-auto"><a href="/reportWeek?departmentId=${department.id}" class="btn btn-primary">Last week</a></div>
                    <div class="col"><p class="text-center">From:</p></div>
                    <div class="col"><form:input type="date" id="fromDate" class="form-control" path="fromDate" value="${fromDate}"></form:input></div>
                    <div class="col"><p class="text-center">To:</p></div>
                    <div class="col"><form:input type="date" id="toDate" class="form-control" path="toDate" value="${toDate}"></form:input></div>
                    <div class="col"><button type="submit" class="btn btn-primary">Show</button></div>
                    <div class="col"><form:errors path="fromDate"></form:errors>${dateError}</div>
                </form:form>
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
                            <td scope="raw">${tasks.indexOf(task) + 1}</td>
                            <td>${task.name}</td>
                            <td>${task.description}</td>
                            <td>${task.priority}</td>
                            <td><c:forEach items="${task.employees}" var="executor">
                                <p>${executor.name}</p></c:forEach></td>
                            <fmt:formatDate value="${task.deadLine}" pattern="yyyy-MM-dd HH:mm:ss" var="deadline"/>
                            <td>${deadline}</td>
                            <td>
                                <c:if test="${task.condition=='in_process'}">
                                    <button type="button" class="btn btn-info btn-sm" disabled>In process</button></c:if>
                                <c:if test="${task.condition=='confirmed'}">
                                    <button type="button" class="btn btn-secondary btn-sm" disabled>Confirmed</button></c:if>
                                <c:if test="${task.condition=='done'}">
                                    <button type="button" class="btn btn-success btn-sm"disabled>Done</button></c:if>
                                <c:if test="${task.condition=='failed'}">
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
</body>
</html>