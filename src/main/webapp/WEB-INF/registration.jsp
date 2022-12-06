<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
      <title>Registration</title>
      <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/checkout/">
      <style>
          <%@include file='css/form-validation.css' %>
      </style>
</head>

<body class="bg-light">
  <jsp:include page="common/header.jsp"/>
    <div class="container">
        <main>
           <div class="row g-5">
              <div class="col-md-7 col-lg-8">
                    <h4 class="mb-3">Registration</h4>
                    <form:form class="needs-validation" method="POST" modelAttribute="userForm">
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label for="firstName" class="form-label">First name</label>
                                <form:input type="text" class="form-control" path="firstName" id="firstName" placeholder="First name"></form:input>
                            </div>
                            <div class="col-sm-6">
                                <label for="lastName" class="form-label">Last name</label>
                                <form:input type="text" class="form-control" path="lastName" id="lastName" placeholder="Last name"></form:input>
                            </div>
                            <div class="col-sm-12">
                              <label for="email" class="form-label">E-mail</label>
                              <form:input type="email" class="form-control" path="email" id="email" placeholder="E-mail" autofocus="true"></form:input>
                              <form:errors path="email"></form:errors> <div class="error text-center">${emailError}</div>
                            </div>
                            <div class="col-sm-12">
                              <label for="username" class="form-label">Username</label>
                                <div class="input-group has-validation">
                                  <span class="input-group-text">@</span>
                                  <form:input type="text" class="form-control" path="username" id="username" placeholder="Username" autofocus="true"></form:input>
                                  <form:errors path="username"></form:errors> <div class="error text-center">${usernameError}</div>
                                </div>
                            </div>
                            <div class="col-sm-12">
                              <label for="password" class="form-label">Password</label>
                              <form:input type="password" class="form-control" path="password" id="password" placeholder="Password"></form:input>
                            </div>
                            <div class="col-sm-12">
                              <label for="confirmPassword" class="form-label">Confirm password</label>
                              <form:input type="password" class="form-control" path="confirmPassword" id="confirmPassword" placeholder="Confirm your password"></form:input>
                              <form:errors path="password"></form:errors> <div class="error text-center">${passwordError}</div>
                            </div><br>
                        </div>
                        <hr class="my-4"><br>
                    <button class="w-100 btn btn-primary btn-lg" type="submit">Register</button>
                    </form:form>
              </div>
           </div>
        </main>
    </div>
</body>
</html>
