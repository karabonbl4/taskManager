<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customers</title>
</head>
<style>
</style>
<body>
<jsp:include page="departmentDetails.jsp"/>
<div>
<c:if test = "${customers.size() == 0}">
    <h4>Customers not found</h4>
</c:if>
</div>
<c:if test = "${customers.size() > 0}">
    <table>
      <tr>
        <th>Name</th>
        <th>Location</th>
        <th>Tax number</th>
        <th>Email</th>
      </tr>
      <c:forEach items="${customers}" var="customer">
        <tr>
          <td>${customer.name}</td>
          <td>${customer.location}</td>
          <td>${customer.taxNumber}</td>
          <td>${customer.email}</td>
        </tr>
      </c:forEach>
    </table>
</c:if>
</body>
</html>