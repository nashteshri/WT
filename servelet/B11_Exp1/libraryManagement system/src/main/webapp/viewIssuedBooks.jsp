<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Issued Books</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 text-gray-800">

<header class="mb-8">
    <nav class="bg-teal-600 border-gray-600">
        <div class="max-w-screen-xl flex items-center justify-between mx-auto p-4">
            <a href="index.jsp" class="text-white text-lg font-bold">Library Management System</a>
            <div class="w-full flex items-center justify-center" id="navbar-default">
                <ul class="font-medium flex space-x-8 mt-0 border-0 bg-teal-600">
                    <li>
                        <a href="addBook.jsp" class="block py-2 px-3 text-white rounded hover:bg-teal-500 hover:text-yellow-300">Add Book</a>
                    </li>
                    <li>
                        <a href="addStudent.jsp" class="block py-2 px-3 text-white rounded hover:bg-teal-500 hover:text-yellow-300">Add Student</a>
                    </li>
                    <li>
                        <a href="issueBook.jsp" class="block py-2 px-3 text-white rounded hover:bg-teal-500 hover:text-yellow-300">Issue Book</a>
                    </li>
                    <li>
                        <a href="viewIssuedBooks.jsp" class="block py-2 px-3 text-yellow-300 bg-teal-500 rounded">View Issued Books</a>
                    </li>
                    <li>
                        <a href="index.jsp" class="block py-2 px-3 text-white rounded hover:bg-teal-500 hover:text-yellow-300">Home</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="mx-auto max-w-2xl p-6 bg-white rounded-lg shadow-lg">

    <h1 class="text-3xl font-bold mb-4 text-teal-600">Issued Books</h1>

    <div class="relative overflow-x-auto shadow-md sm:rounded-lg mb-8">
        <table class="w-full text-sm text-left text-gray-600">
            <thead class="text-xs text-gray-500 uppercase bg-gray-200">
            <tr>
                <th scope="col" class="px-6 py-3">Book Title</th>
                <th scope="col" class="px-6 py-3">Author</th>
                <th scope="col" class="px-6 py-3">Student Name</th>
            </tr>
            </thead>

            <tbody>
            <jsp:useBean id="issuedBookController" class="com.example.servlet_jsp.controller.IssuedBookController"/>
            <c:forEach var="issuedBookWithDetails" items="${issuedBookController.issuedBooks}">
                <tr class="odd:bg-white even:bg-gray-100 border-b border-gray-200">
                    <td class="px-6 py-4">${issuedBookWithDetails.book.title}</td>
                    <td class="px-6 py-4">${issuedBookWithDetails.book.author}</td>
                    <td class="px-6 py-4">${issuedBookWithDetails.student.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <a href="<c:url value='/index.jsp'/>" class="inline-block mt-4 px-4 py-2 bg-teal-500 text-white rounded hover:bg-teal-600 shadow-lg">Back to Home</a>
</div>

</body>
</html>
