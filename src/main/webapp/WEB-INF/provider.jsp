<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Providers</title>
</head>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<body>
    <jsp:include page="departmentDetails.jsp"/>
    <div>
        <c:if test = "${providers.size() == 0}">
            <h4>Providers not found</h4>
        </c:if>
    </div>
    <div>
    <br>
        <c:if test = "${providers.size() > 0}">
            <table>
              <tr>
                <th>Name</th>
                <th>Location</th>
                <th>Tax number</th>
                <th>Email</th>
              </tr>
              <c:forEach items="${providers}" var="provider">
                <tr>
                  <td>${provider.name}</td>
                  <td>${provider.location}</td>
                  <td>${provider.taxNumber}</td>
                  <td>${provider.email}</td>
                </tr>
              </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>