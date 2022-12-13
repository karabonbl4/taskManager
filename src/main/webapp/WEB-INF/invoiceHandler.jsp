<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Handle of invoice</title>
</head>
<body>
    <jsp:include page="common/header.jsp"/>
    <div class="container">
        <div class="row g-5">
            <div class="col-md-7 col-lg-8">
                <form:form method="POST" modelAttribute="newEmployee">
                      <div class="card border-primary mb-3 text-center" style="max-width: 18rem;">
                            <div class="card-header">Offer</div>
                                <div class"card-body">
                                <h5 class="card-title">${department.name}, ${department.location}</h5>
                                   <div class="mb-3 row">
                                       <label for="jobTitle" class="col-sm-6 col-form-label">Job title</label>
                                      <div class="col-sm-6">
                                        <form:input type="text" readonly="true" class="form-control-plaintext" path="jobTitle" id="jobTitle" value="${newEmployee.jobTitle}" />
                                      </div>
                                   </div>
                                  <div class="mb-3 row">
                                       <label for="username" class="col-sm-6 col-form-label">Username</label>
                                  <div class="col-sm-6">
                                       <form:input type="text" readonly="true" class="form-control-plaintext" path="username" id="username" value="${username}" />
                                  </div>
                                  </div>
                                    <div>
                                        <div>
                                            <form:input type="hidden" path="departmentId" value="${department.id}"></form:input>
                                        </div>
                                    </div>
                                </div>
                      </div>
                    <input type="submit" class="btn btn-primary" name="submit" value="Agree"/>
                    <input type="submit" class="btn btn-danger" name="cancel" value="Disagree"/>
                </form:form>
            </div>
            <jsp:include page="departmentDetails.jsp"/>
          <jsp:include page="common/footer.jsp"/>
        </div>
    </div>
</body>
</html>