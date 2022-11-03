<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
</head>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<body>
<div>
  <form:form method="POST" modelAttribute="newDepartment">
    <h2>Add new department</h2>
    <div>
          <form:input type="name" path="name" placeholder="Name"></form:input>
    </div>
    <div>
          <form:input type="location" path="location" placeholder="Location"></form:input>
    </div>
    <button type="submit">Add department</button>
  </form:form>
</div>
<c:if test = "${departments.size() == 0}">
    <h4>Departments not found</h4>
</c:if>
<c:if test = "${departments.size() > 0}">
    <table>
      <tr>
        <th>Name</th>
        <th>Location</th>
        <th></th>
      </tr>
      <c:forEach items="${departments}" var="department">
        <tr>
          <td>${department.name}</td>
          <td>${department.location}</td>
          <td><button type="submit" value="${department.name}">Choose</button></td>
        </tr>
      </c:forEach>
    </table>
</c:if>
</body>
</html>