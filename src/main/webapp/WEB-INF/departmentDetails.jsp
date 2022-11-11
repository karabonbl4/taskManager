<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${department.name}</title>
</head>
<body>
<c:if test="${department.authUserFunction=='manager'}">
    <jsp:include page="common/header.jsp"/>
</c:if>
<div>
    <a href = "/task?department_id=${department.id}&calendar=">Tasks</a> |
    <a href = "/department">Back to departments</a> |
    <a href = "/logout">Log out</a>
</div>
<div>
  <h4>Info:</h4>
  <p>Department: ${department.name} | location: ${department.location}</p>
  <p>Username: ${pageContext.request.userPrincipal.name} | function: ${department.authUserFunction}</p>
</div>
</body>
</html>