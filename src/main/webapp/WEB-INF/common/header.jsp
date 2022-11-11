<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
    <div>
        <a href = "/customer?department_id=${department.id}">Customers</a> |
        <a href = "/provider?department_id=${department.id}">Providers</a> |
        <a href = "/material?department_id=${department.id}">Materials</a> |
        <a href = "/employee?department_id=${department.id}">Employees</a> |
        <a href = "/report?department_id=${department.id}">Reports</a>
    </div><br>
</body>
</html>