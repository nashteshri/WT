<%--
  Created by IntelliJ IDEA.
  User: mahesh
  Date: 20-08-2024
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--%@ taglib uri="jakarta.tags.core" prefix="c" %-->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">


        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Students</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${student != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${student == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${student != null}">
                                Edit Student
                            </c:if>
                            <c:if test="${student == null}">
                                Add New Student
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${student != null}">
                        <input type="hidden" name="prn" value="<c:out value='${student.prn}' />" />
                    </c:if> 
                    <c:if test="${student == null}">
                        <fieldset class="form-group">
                        	<label>Student PRN</label> <input type="text"
                                                         value="<c:out value='${student.prn}' />" class="form-control"
                                                         name="prn">
                    	</fieldset>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Student Name</label> <input type="text"
                                                        value="<c:out value='${student.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>


                    <fieldset class="form-group">
                        <label>Student Department</label> <input type="text"
                                                           value="<c:out value='${student.branch}' />" class="form-control"
                                                           name="branch">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>

