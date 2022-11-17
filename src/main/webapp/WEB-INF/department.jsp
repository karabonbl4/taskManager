<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Departments</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/grid/">
    <style>
         <%@include file='css/grid.css' %>
    </style>
  <%@include file='index.jsp' %>
</head>
<body>
<main>
<div class="container">
  <form:form class="row g-3" method="POST" modelAttribute="newDepartment">
    <div class="col-auto">
     <label for="staticEmail2" class="visually-hidden">Title</label>
          <form:input type="text" class="form-control" id="staticEmail2" path="name" placeholder="Title"></form:input>
          <form:errors path="name"></form:errors>
          ${departmentError}
    </div>
    <div class="col-auto">
      <label for="staticEmail2" class="visually-hidden">Location</label>
          <form:input type="text" class="form-control" id="staticEmail2" path="location" placeholder="Location"></form:input>
    </div>
    <div class="col-auto">
    <button type="submit" class="btn btn-primary mb-3">Add department</button>
    </div>
  </form:form>
<c:if test = "${departments.size() == 0}">
    <h4>Departments not found</h4>
</c:if>
<c:if test = "${departments.size() > 0}">
    <div class="container-fluid"><h1 class="display-6">Your departments</h1></div>
    <table class="table">
    <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Location</th>
        <th scope="col">Function</th>
        <th scope="col">Manager</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${departments}" var="department">
        <tr>
          <td><a href="/task?department_id=${department.id}&calendar=">${department.name}</td>
          <td>${department.location}</td>
          <td>${department.authUserFunction}</td>
          <td>${department.manager}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
</c:if>
</div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>