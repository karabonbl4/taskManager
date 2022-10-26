<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Registration</title>
</head>

<body>
<div>
  <form:form method="POST" modelAttribute="userForm">
    <h2>Registration</h2>
    <div>
          <form:input type="firstName" path="firstName" placeholder="First name"></form:input>
    </div>
    <div>
    <div>
          <form:input type="lastName" path="lastName" placeholder="Last name"></form:input>
    </div>
    <div>
      <form:input type="text" path="email" placeholder="E-mail"
                  autofocus="true"></form:input>
      <form:errors path="email"></form:errors>
        ${emailError}
    </div>
    <div>
      <form:input type="text" path="username" placeholder="Username"
                  autofocus="true"></form:input>
      <form:errors path="username"></form:errors>
        ${usernameError}
    </div>
    <div>
      <form:input type="password" path="password" placeholder="Password"></form:input>
    </div>
    <div>
      <form:input type="password" path="confirmPassword"
                  placeholder="Confirm your password"></form:input>
      <form:errors path="password"></form:errors>
        ${passwordError}
    </div>
    <button type="submit">Register</button>
  </form:form>
  <a href="/">Home page</a>
</div>
</body>
</html>
