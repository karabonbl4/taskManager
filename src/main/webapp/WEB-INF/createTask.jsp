<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New task</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form action="/createTask?departmentId=${department.id}" method="POST" modelAttribute="newTask">
                    <h2 class="display-6">Create new task</h2>
                        <div class="mb-3 row">
                            <label for="name" class="col-sm-2 col-form-label">Title</label>
                            <div class="col-sm-10">
                                <form:input type="text" class="form-control" path="name" id="name" name="title"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="description" class="col-sm-2 col-form-label">Description</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <form:textarea class="form-control" path="description" id="description" rows="2"></form:textarea>
                                    </div>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="deadlineDate" class="col-sm-2 col-form-label">Deadline day</label>
                                <div class="col-auto">
                                    <form:input type="date" class="form-control" id="deadlineDate" path="deadlineDate" required="required"></form:input>
                                </div>
                            <label for="deadlineTime" class="col-sm-2 col-form-label">Deadline time</label>
                                <div class="col-auto">
                                    <form:input type="time" class="form-control" id="deadlineTime" path="deadlineTime" required="required"></form:input>
                                </div>
                                <div class="col-auto">
                                    <form:input type="hidden" path="tempMaterials" name="tempMaterials" value="${newTask.tempMaterials}"></form:input>
                                    <c:if test="${empty newTask.tempMaterials}">
                                        <input type="submit" class="btn btn-primary" name="addMaterial" value="+ Material"/>
                                    </c:if>
                                    <c:if test="${!empty newTask.tempMaterials}">
                                        <input type="submit" class="btn btn-primary" name="addMaterial" value="+ Material" disabled/>
                                    </c:if>
                                </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="workday" class="col-sm-2 col-form-label">Workday</label>
                            <div class="col-sm-10">
                                <form:input type="date" class="form-control" id="workday" path="workday" required="required"></form:input>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="floatingPriority" class="col-sm-2 col-form-label">Task priority</label>
                                <div class="col-sm-10">
                                    <form:select class="form-select" id="floatingPriority" path="priority">
                                        <option selected value="1">Choose priority for task</option>
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
                                    <form:select class="form-select" id="floatingExecutors" path="executors" multiple="multiple" required="required">
                                        <c:forEach items="${employees}" var="executor">
                                            <option value="${executor.id}">${executor.jobTitle}</option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                        </div>
                    <input type="submit" class="btn btn-primary" name="create" value="Create"/>
                    <a href="/task?departmentId=${department.id}" class="btn btn-link">Back to Tasks</a>
                </form:form>
            </div>
            <jsp:include page="departmentDetails.jsp"/>
          <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>