<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Log in</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <meta name="generator" content="Hugo 0.104.2">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">
   <style>
        <%@include file='css/signin.css' %>
   </style>
</head>

<body class="text-center">
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<main class="form-signin w-100 m-auto">
    <form method="POST" action="/login">
        <img class="mb-4" src="https://files.fm/thumb_show.php?i=sgx9pc45f" alt="" width="116" height="116">
             <h1 class="h3 mb-3 fw-normal">Authentication</h1>

        <div class="form-floating">
            <input type="floatingInput" class="form-control" id="floatingInput" name="username" placeholder="Login">
            <label for="floatingInput">Login</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div><br>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        <h5><a href="/registration">Register</a></h5><br>
 <p class="mt-5 mb-3 text-muted">© 2022</p>
 </form>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>