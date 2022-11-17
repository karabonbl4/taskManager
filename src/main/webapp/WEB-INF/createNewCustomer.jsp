<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New customer</title>
</head>
<style>
</style>
<body>
<jsp:include page="departmentDetails.jsp"/>
<form:form method="POST" modelAttribute="newCustomer">
    <h2>Create new customer</h2>
    <div>
          <form:input path="name" placeholder="Title"></form:input>
    </div>
    <div>
          <form:input path="taxNumber" placeholder="Last name"></form:input>
    </div>
    <div>
      <form:input path="email" placeholder="E-mail"></form:input>
    </div>
    <div>
      <form:input type="text" path="username" placeholder="Username"
                  autofocus="true"></form:input>
      <form:errors path="username"></form:errors>
        ${usernameError}
    </div>

</body>
</html>