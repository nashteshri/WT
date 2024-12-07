<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.servlet_jsp.controller.BookController" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-900">

<!-- Header -->
<header class="mb-8">
    <nav class="bg-teal-600">
        <div class="max-w-screen-xl mx-auto p-4">
            <ul class="flex justify-center space-x-10">
                <li><a href="addBook.jsp" class="text-white hover:text-yellow-300">Add New Book</a></li>
                <li><a href="addStudent.jsp" class="text-white hover:text-yellow-300">Add Student</a></li>
                <li><a href="issueBook.jsp" class="text-white hover:text-yellow-300">Issue Book</a></li>
                <li><a href="viewIssuedBooks.jsp" class="text-white hover:text-yellow-300">View Issued Books</a></li>
                <li><a href="index.jsp" class="text-white hover:text-yellow-300">Home</a></li>
            </ul>
        </div>
    </nav>
</header>

<!-- Form Section -->
<div class="max-w-md mx-auto p-8 bg-white rounded-lg shadow-lg">
    <h2 class="text-3xl font-bold mb-6 text-center text-teal-600">Add Book</h2>

    <form action="addBook.jsp" method="post">
        <div class="mb-5">
            <label for="title" class="block text-lg mb-2 text-gray-700">Title</label>
            <input type="text" id="title" name="title" required class="w-full p-3 border border-gray-300 rounded-lg text-gray-900 bg-gray-100">
        </div>
        <div class="mb-5">
            <label for="author" class="block text-lg mb-2 text-gray-700">Author</label>
            <input type="text" id="author" name="author" required class="w-full p-3 border border-gray-300 rounded-lg text-gray-900 bg-gray-100">
        </div>
        <div class="mb-5">
            <label for="assNo" class="block text-lg mb-2 text-gray-700">AssNo</label>
            <input type="text" id="assNo" name="assNo" required class="w-full p-3 border border-gray-300 rounded-lg text-gray-900 bg-gray-100">
        </div>
        <button type="submit" class="w-full bg-teal-500 hover:bg-teal-700 text-white py-3 rounded-lg text-lg">Add Book</button>
    </form>

    <a href="<c:url value='/index.jsp'/>" class="block mt-6 text-center text-teal-600 hover:underline">Back to Home</a>
</div>

<!-- Success or Error Messages -->
<c:if test="${pageContext.request.method=='POST'}">
    <jsp:useBean id="bookController" class="com.example.servlet_jsp.controller.BookController"/>
    <c:catch var="exception">
        ${bookController.addBook(param.title, param.author, param.assNo)}
        <div class="mt-4 text-center text-lg text-green-500">Book added successfully!</div>
    </c:catch>
    <c:if test="${not empty exception}">
        <div class="mt-4 text-center text-lg text-red-500">Error: ${exception.message}</div>
    </c:if>
</c:if>

</body>
</html>
