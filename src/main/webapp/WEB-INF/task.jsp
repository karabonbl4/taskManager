<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Tasks</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <c:set var="calendar" value=""/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <div>
                    <form:form method="GET" action="/task" modelAttribute="workDayWithDepartmentIdDto">
                        <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                            <input type="submit" class="w-100 btn btn-primary btn-lg" value="Tasks"></input>
                        </form:form>
                    </div>
                <c:if test="${department.authUserFunction=='manager'}">
                    <div class="text-end">
                        <a href = "/createTask?departmentId=${department.id}" class="btn btn-outline-success">Create new task</a>
                    </div>
                </c:if>
                <div>
                   <h1 class="display-6">Choose the date to see your tasks:</h1>
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
                               <th scope="col">Title</th>
                               <th scope="col">Description</th>
                               <th scope="col">Priority</th>
                               <th scope="col">Executors</th>
                               <th scope="col">Deadline</th>
                               <th scope="col">Condition</th>
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
                                   <td><form:input type="hidden" path="executors" value="${editTask.executors}"></form:input>
                                   <c:forEach items="${task.employees}" var="executor">
                                        <p>${executor.name}</p></c:forEach></td>
                                   <fmt:formatDate value="${task.deadLine}" pattern="yyyy-MM-dd" var="deadLineDate"/>
                                   <td><form:input type="hidden" path="deadlineDate" value="${deadLineDate}"></form:input>${task.deadLine}</td>
                                   <td><form:input type="hidden" path="condition" value="${task.condition}"></form:input>
                                      <c:if test="${task.condition=='in_process'}">
                                        <input type="submit" class="btn btn-light btn-sm" name="execute" value="Execute"/></c:if>
                                      <c:if test="${task.condition=='confirmed'}">
                                        <c:if test="${department.authUserFunction=='manager'}">
                                          <div><input type="submit" class="btn btn-success btn-sm" name="confirm" value="Confirm"/></div>
                                          <div><input type="submit" class="btn btn-warning btn-sm" name="toWork" value="To work"/></div>
                                        </c:if>
                                        <c:if test="${department.authUserFunction!='manager'}">
                                        <input type="button" class="btn btn-secondary btn-sm" value="Checked" disabled/></c:if>
                                      </c:if>
                                      <c:if test="${task.condition=='done'}">
                                         <input type="button" class="btn btn-success btn-sm" value="Done" disabled/></c:if>
                                      <c:if test="${task.condition=='failed'}">
                                         <input type="button" class="btn btn-danger btn-sm" value="Failed" disabled/></c:if>
                                   </td>
                                   <fmt:formatDate value="${task.deadLine}" pattern="HH:mm" var="deadLineTime"/>
                                   <form:input type="hidden" path="deadlineTime" value="${deadLineTime}"></form:input>
                                   <form:input type="hidden" path="workday" value="${task.workday}"></form:input>
                                   <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                                   <td>
                                       <c:if test = "${department.authUserFunction=='manager'}">
                                           <input type="submit" name="edit" class="btn btn-secondary btn-sm" value="Edit"/>
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


