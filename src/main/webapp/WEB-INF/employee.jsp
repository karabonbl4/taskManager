<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
</head>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<body>
    <jsp:include page="departmentDetails.jsp"/>
       <a href = "invoiceEmployee?department_id=${department.id}">Invite an employee</a>
    <div>
        <c:if test = "${employees.size() == 0}">
            <h4>Employees not found</h4>
        </c:if>
    </div>
    <div>
    <br>
        <c:if test = "${employees.size() > 0}">
            <table>
              <tr>
                <th>Username</th>
                <th>Function</th>
              </tr>
              <c:forEach items="${employees}" var="employee">
                <tr>
                  <td>${employee.username}</td>
                  <td>${employee.jobTitle}</td>
                </tr>
              </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>