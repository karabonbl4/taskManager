<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit task</title>
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
                <form:form action="/editTask?departmentId=${editTask.departmentId}" method="POST" modelAttribute="editTask">
                    <h2 class="display-6">Edit ${editTask.name}</h2>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-2 col-form-label">Title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="name" id="name" value="${editTask.name}"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="description" class="col-sm-2 col-form-label">Description</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <form:textarea class="form-control" path="description" id="description" rows="2" value="${editTask.description}"></form:textarea>
                                    </div>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="deadlineDate" class="col-sm-2 col-form-label">Deadline day</label>
                                <div class="col-auto">
                                    <form:input type="date" class="form-control" id="deadlineDate" path="deadlineDate"></form:input>
                                </div>
                            <label for="deadlineTime" class="col-sm-2 col-form-label">Deadline time</label>
                                <div class="col-auto">
                                    <form:input type="time" class="form-control" id="deadlineTime" path="deadlineTime" value="${editTask.deadlineTime}"></form:input>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="workday" class="col-sm-2 col-form-label">Workday</label>
                            <div class="col-auto">
                                <form:input type="date" class="form-control" id="workday" path="workday"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="floatingPriority" class="col-sm-2 col-form-label">Task priority</label>
                                <div class="col-sm-10">
                                    <form:select class="form-select" id="floatingPriority" path="priority">
                                        <option selected value="${editTask.priority}">${editTask.priority}</option>
                                        <option value="1">&#9734</option>
                                        <option value="2">&#9734&#9734</option>
                                        <option value="3">&#9734&#9734&#9734</option>
                                        <option value="4">&#9734&#9734&#9734&#9734</option>
                                        <option value="5">&#9734&#9734&#9734&#9734&#9734</option>
                                    </form:select>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="floatingExecutors" class="col-sm-2 col-form-label">Executors</label>
                                <div class="col-sm-10">
                                <form:select class="form-select" id="floatingExecutors" path="executors" multiple="multiple">
                                        <c:forEach items="${employees}" var="executor">
                                            <option value="${executor.id}">${executor.jobTitle}</option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <form:input type="hidden" path="id" value="${editTask.id}"></form:input>
                            <form:input type="hidden" path="departmentId" value="${editTask.departmentId}"></form:input>
                        </div>
                        <form:errors path="id"></form:errors>${taskError}
                    <button type="submit" class="btn btn-primary">Edit</button>
                    <a href="/task?departmentId=${editTask.departmentId}" class="btn btn-link">Back to Tasks</a>
                </form:form>
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