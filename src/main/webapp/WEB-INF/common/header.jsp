<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Homepage</title>
  <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/headers/">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
  <style>
      <%@include file='/WEB-INF/css/headers.css' %>
  </style>
</head>
<body>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;"></svg>
    <main>
        <div class="container">
            <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
                <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                    <svg class="bi me-2" width="45" height="45" role="img" aria-label="TaskManager"><image width="45" height="45" xlink:href="https://files.fm/thumb_show.php?i=sgx9pc45f"></svg>
                </a>
                    <c:if test="${department.authUserFunction=='manager'}">
                        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                            <li><a href="/customer?departmentId=${department.id}" class="nav-link px-2 link-secondary">Customers</a></li>
                            <li><a href="/provider?departmentId=${department.id}" class="nav-link px-2 link-secondary">Providers</a></li>
                            <li><a href="/material?departmentId=${department.id}" class="nav-link px-2 link-secondary">Materials</a></li>
                            <li><a href="/employee?departmentId=${department.id}" class="nav-link px-2 link-secondary">Employees</a></li>
                            <li><a href="/report?departmentId=${department.id}" class="nav-link px-2 link-secondary">Reports</a></li>
                        </ul>
                    </c:if>
                    <sec:authorize access="isAuthenticated()">
                        <div class="col-md-3 text-end">
                            <a href="/department" class="btn btn-outline-primary me-2">Departments</a>
                            <a href="/logout" class="btn btn-outline-primary me-2">Log out</a>
                        </div>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <div class="col-md-3 text-end">
                            <button type="button" onclick="document.location='login'" class="btn btn-outline-primary me-2">Login</button>
                            <button type="button" onclick="document.location='registration'" class="btn btn-outline-primary me-2">Register</button>
                        </div>
                    </sec:authorize>
                </header>
        </div>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>