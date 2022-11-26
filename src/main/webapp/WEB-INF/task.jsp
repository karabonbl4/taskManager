<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Tasks</title>
</head>
<body>
    <c:if test="${department.authUserFunction=='manager'}">
        <jsp:include page="common/header.jsp"/>
    </c:if>
    <c:if test="${department.authUserFunction!='manager'}">
        <jsp:include page="index.jsp"/>
    </c:if>
    <c:set var="calendar" value=""/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <div>
                    <form:form method="GET" action="/task" modelAttribute="workDayWithDepartmentIdDto">
                        <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                            <input type="submit" class="w-100 btn btn-primary btn-lg" value="Tasks"></input>
                        </form:form>
                    </div><br>
                <c:if test="${department.authUserFunction=='manager'}">
                    <div>
                        <a href = "/createTask?departmentId=${department.id}">Create new task</a><br>
                    </div>
                </c:if>
                <div>
                   <h5 class="display 6">Choose the date to see your tasks:</h5>
                   <form:form method="POST" class="row g-3" modelAttribute="workDayWithDepartmentIdDto">
                          <div class="col-auto">
                              <form:input type="hidden"  path="departmentId" value="${department.id}"></form:input>
                          </div>
                          <div class="col-auto">
                              <form:input type="date" class="form-control" path="date"></form:input>
                          </div>
                          <div class="col-auto">
                              <button type="submit" class="btn btn-primary">Choose</button>
                          </div>
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
                               <th scope="col">Name</th>
                               <th scope="col">Description</th>
                               <th scope="col">Priority</th>
                               <th scope="col">Executors</th>
                               <th scope="col">Deadline</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${tasks}" var="task">
                            <tr>
                               <td scope="raw">${tasks.indexOf(task) + 1}</td>
                               <form:form action="/editTask" method="GET" modelAttribute="editTask">
                                   <form:input type="hidden" path="id" value="${task.id}"></form:input>
                                   <td><form:input type="hidden" path="name" value="${task.name}"></form:input>${task.name}</td>
                                   <td><form:input type="hidden" path="description" value="${task.description}"></form:input>${task.description}</td>
                                   <td><form:input type="hidden" path="priority" value="${task.priority}"></form:input>${task.priority}</td>
                                   <td><form:input type="hidden" path="executors" value="${task.employees}"></form:input>
                                   <c:forEach items="${task.employees}" var="executor">
                                        <p>${executor.name}</p></c:forEach></td>
                                   <fmt:formatDate value="${task.deadLine}" pattern="yyyy-MM-dd" var="deadLineDate"/>
                                   <td><form:input type="hidden" path="deadlineDate" value="${deadLineDate}"></form:input>${task.deadLine}</td>
                                   <fmt:formatDate value="${task.deadLine}" pattern="HH:mm" var="deadLineTime"/>
                                   <form:input type="hidden" path="deadlineTime" value="${deadLineTime}"></form:input>
                                   <form:input type="hidden" path="workday" value="${task.workday}"></form:input>
                                   <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                                   <td>
                                       <c:if test = "${department.authUserFunction=='manager'}">
                                           <button type="submit" class="btn btn-secondary btn-sm">Edit</button>
                                       </c:if>
                                   </td>
                               </form:form>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                </div>
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


