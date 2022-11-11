<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Handle of invoice</title>
</head>
<body>
    <jsp:include page="departmentDetails.jsp"/>
    <div>
        <form:form method="POST" modelAttribute="newEmployee">
            <h2>Offer</h2>
            <form:label path="jobTitle">Job title:<form:input path="jobTitle" value="${newEmployee.jobTitle}" /></form:label><br>
            <form:label path="username">Username:<form:input path="username" value="${username}" /></form:label><br>
            <form:input type="hidden" path="departmentId" value="${departmentId}" />
            <input type="submit" name="submit" value="Agree" /><input type="submit" name="cancel" value="Disagree" />
          </form:form>
    </div>
    <div>
    <br>
        <c:if test = "${employees.size() > 0}">
            <table>
              <tr>
                <th>Username</th>
                <th>Function</th>
              </tr>
              <c:forEach items="${employees}" var="employee">
                <tr>
                  <td>${employee.username}</td>
                  <td>${employee.name}</td>
                </tr>
              </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>