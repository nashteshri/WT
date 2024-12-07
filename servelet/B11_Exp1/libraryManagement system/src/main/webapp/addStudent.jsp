<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.servlet_jsp.controller.StudentController" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 text-gray-900">

<!-- Header -->
<header class="mb-8">
    <nav class="bg-teal-600">
        <div class="max-w-screen-xl mx-auto p-4">
            <ul class="flex justify-center space-x-10">
                <li>
                    <a href="addBook.jsp" class="text-white hover:text-teal-200">Add Book</a>
                </li>
                <li>
                    <a href="addStudent.jsp" class="text-yellow-400">Add Student</a>
                </li>
                <li>
                    <a href="issueBook.jsp" class="text-white hover:text-teal-200">Issue Book</a>
                </li>
                <li>
                    <a href="viewIssuedBooks.jsp" class="text-white hover:text-teal-200">View Issued Books</a>
                </li>
                <li>
                    <a href="index.jsp" class="text-white hover:text-teal-200">Home</a>
                </li>
            </ul>
        </div>
    </nav>
</header>

<!-- Form Section -->
<div class="container mx-auto max-w-lg">
    <div class="bg-white shadow-lg rounded-lg p-8 border border-gray-200">
        <h2 class="text-3xl font-bold mb-6 text-teal-600">Add Student</h2>

        <form action="addStudent.jsp" method="post">
            <div class="mb-6">
                <label for="name" class="block text-lg font-medium text-gray-700 mb-2">Student Name</label>
                <input type="text" id="name" name="name" required class="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-400">
            </div>

            <div class="mb-6">
                <label for="email" class="block text-lg font-medium text-gray-700 mb-2">Email</label>
                <input type="email" id="email" name="email" required class="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-400">
            </div>

            <button type="submit" class="w-full bg-yellow-500 hover:bg-yellow-600 text-white font-bold py-2 px-4 rounded-lg">Add Student</button>
        </form>

        <a href="<c:url value='/index.jsp'/>" class="block text-sm mt-4 text-teal-600 hover:underline">Back to Home</a>
    </div>
</div>

<!-- Success or Error Messages -->
<c:if test="${pageContext.request.method=='POST'}">
    <jsp:useBean id="studentController" class="com.example.servlet_jsp.controller.StudentController"/>
    <c:catch var="exception">
        ${studentController.addStudent(param.name, param.email)}
        <div class="mx-auto max-w-lg mt-4 p-4 text-green-600 bg-green-100 rounded-lg border border-green-500">Student added successfully!</div>
    </c:catch>
    <c:if test="${not empty exception}">
        <div class="mx-auto max-w-lg mt-4 p-4 text-red-600 bg-red-100 rounded-lg border border-red-500">Error: ${exception.message}</div>
    </c:if>
</c:if>

</body>
</html>
