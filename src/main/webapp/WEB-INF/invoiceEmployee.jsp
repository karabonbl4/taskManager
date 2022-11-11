<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Invoice an employee</title>
</head>
<body>
    <jsp:include page="departmentDetails.jsp"/>
    <div>
        <form:form method="POST" modelAttribute="newEmployee">
            <h2>Invite new employee</h2>
            <div>
                  <form:input type="jobTitle" path="jobTitle" placeholder="Job title"></form:input>
                  <form:errors path="jobTitle"></form:errors>
                  ${employeeError}
            </div>
            <div>
                  <form:input type="email" path="email" placeholder="Email"></form:input>
            </div>
            <div>
                  <form:input type="text" path="departmentId" value="${department.id}"></form:input>
            </div>
            <button type="submit">Invite</button>
          </form:form>
    </div>
</body>
</html>