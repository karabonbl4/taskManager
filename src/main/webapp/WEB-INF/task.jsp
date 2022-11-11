<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tasks</title>
</head>
<body>
    <jsp:include page="departmentDetails.jsp"/>
    <div>
       <form>
          <p>Choose the date to see your tasks: <input type="date" name="calendar">
               <input type="submit" value="Choose"></p>
       </form>
    </div>
    <div>
        <c:if test = "${tasks.size() == 0}">
            <h4>Tasks not found</h4>
        </c:if>
    </div>
    <div>
        <c:if test = "${tasks.size() > 0}">
            <table>
              <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Priority</th>
                <th>Executor</th>
                <th>Deadline</th>
              </tr>
              <c:forEach items="${tasks}" var="task">
                <tr>
                  <td>${task.name}</td>
                  <td>${task.description}</td>
                  <td>${task.priority}</td>
                  <td>${task.executor}</td>
                  <td>${task.deadline}</td>
                </tr>
              </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>