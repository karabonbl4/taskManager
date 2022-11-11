<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
  <title>Homepage</title>
</head>
<body>
<div>
  <h3>${pageContext.request.userPrincipal.name}</h3>
  <sec:authorize access="!isAuthenticated()">
    <h4><a href="/login">Log in</a></h4>
    <h4><a href="/registration">Register</a></h4>
  </sec:authorize>
  <sec:authorize access="isAuthenticated()">
    <h4><a href="/department">Department</a></h4>
    <h4><a href="/logout">Log out</a></h4>
  </sec:authorize>
</div>
</body>
</html>