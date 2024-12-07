<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Issue Book</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-900 text-gray-200 min-h-screen">

<!-- Header -->
<header class="mb-8">
    <nav class="bg-gray-900 border-gray-700">
        <div class="max-w-screen-xl flex items-center justify-center mx-auto p-4">
            <div class="w-full flex items-center justify-center" id="navbar-default">
                <ul class="font-medium flex space-x-8 mt-0 border-0 bg-gray-900">
                    <li><a href="addBook.jsp" class="block py-2 px-3 text-white rounded hover:text-blue-500">Add Book</a></li>
                    <li><a href="addStudent.jsp" class="block py-2 px-3 text-white rounded hover:text-blue-500">Add Student</a></li>
                    <li><a href="issueBook.jsp" class="block py-2 px-3 text-blue-500">Issue Book</a></li>
                    <li><a href="viewIssuedBooks.jsp" class="block py-2 px-3 text-white rounded hover:text-blue-500">View Issued Books</a></li>
                    <li><a href="index.jsp" class="block py-2 px-3 text-white rounded hover:text-blue-500">Home</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- Main content -->
<div class="container mx-auto max-w-md mb-8">
    <div class="bg-gray-800 shadow-md rounded-lg p-6">
        <h2 class="text-2xl font-bold mb-4 text-white">Issue Book</h2>

        <jsp:useBean id="bookController" class="com.example.servlet_jsp.controller.BookController"/>
        <jsp:useBean id="studentController" class="com.example.servlet_jsp.controller.StudentController"/>

        <c:choose>
            <c:when test="${not empty bookController.allBooks and not empty studentController.allStudents}">
                <form action="issueBook.jsp" method="post">
                    <!-- Book Selection -->
                    <div class="mb-4">
                        <label for="bookId" class="block mb-2 text-sm font-medium text-white">Book</label>
                        <select id="bookId" name="bookId" required
                                class="bg-gray-700 border border-gray-600 text-gray-200 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                            <c:forEach var="book" items="${bookController.allBooks}">
                                <option value="${book.id}">${book.title} by ${book.author}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Student Selection -->
                    <div class="mb-4">
                        <label for="studentId" class="block mb-2 text-sm font-medium text-white">Student</label>
                        <select id="studentId" name="studentId" required
                                class="bg-gray-700 border border-gray-600 text-gray-200 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                            <c:forEach var="student" items="${studentController.allStudents}">
                                <option value="${student.id}">${student.name} (${student.email})</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Submit Button -->
                    <input type="submit" value="Issue Book"
                           class="w-full mt-4 mb-4 text-white bg-blue-600 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                </form>

                <!-- Back to Home Link -->
                <a href="<c:url value='/index.jsp'/>" class="font-medium text-blue-500 hover:underline">Back to Home</a>
            </c:when>
            <c:otherwise>
                <div class="p-4 mb-4 text-sm text-red-400 rounded-lg bg-gray-800" role="alert">
                    No books or students available to issue. Please add books and students first.
                </div>
                <!-- Back to Home Link -->
                <a href="<c:url value='/index.jsp'/>" class="font-medium text-blue-500 hover:underline">Back to Home</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<!-- Success/Error Messages -->
<c:if test="${pageContext.request.method=='POST'}">
    <jsp:useBean id="issuedBookController" class="com.example.servlet_jsp.controller.IssuedBookController"/>
    <c:catch var="exception">
        ${issuedBookController.issueBook(param.bookId, param.studentId)}
        <div class="mx-auto max-w-md p-4 mb-4 text-sm text-green-400 rounded-lg bg-gray-800" role="alert">
            Book issued successfully!
        </div>
    </c:catch>
    <c:if test="${not empty exception}">
        <div class="mx-auto max-w-md p-4 mb-4 text-sm text-red-400 rounded-lg bg-gray-800" role="alert">
            Error issuing book: ${exception.message}
        </div>
    </c:if>
</c:if>

</body>
</html>
