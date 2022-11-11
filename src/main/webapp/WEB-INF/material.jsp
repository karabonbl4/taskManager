<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Materials</title>
</head>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<body>
    <jsp:include page="departmentDetails.jsp"/>
    <div>
        <c:if test = "${materials.size() == 0}">
            <h4>Materials not found</h4>
        </c:if>
    </div>
    <div>
    <br>
        <c:if test = "${materials.size() > 0}">
            <table>
              <tr>
                <th>Name</th>
                <th>Property</th>
                <th>Value</th>
              </tr>
              <c:forEach items="${materials}" var="material">
                <tr>
                  <td>${material.name}</td>
                  <td>${material.property}</td>
                  <td>${material.value}</td>
                </tr>
              </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>