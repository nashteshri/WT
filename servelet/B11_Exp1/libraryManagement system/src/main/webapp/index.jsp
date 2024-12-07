<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Management System</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 text-gray-800 flex items-center justify-center min-h-screen">

<div class="text-center">
    <section class="bg-white shadow-xl rounded-lg p-10 border border-gray-300">
        <div class="py-8 px-4 mx-auto max-w-screen-md">
            <h2 class="mb-6 text-5xl font-bold text-teal-600">Welcome to the Library Management System</h2>

            <div class="mt-8 space-y-4">
                <a href="addBook.jsp" class="block px-8 py-4 bg-teal-500 text-white font-semibold rounded-lg hover:bg-teal-600 focus:ring-4 focus:outline-none focus:ring-teal-300 transition duration-300 ease-in-out text-center">
                    Add Book
                </a>
                <a href="addStudent.jsp" class="block px-8 py-4 bg-indigo-500 text-white font-semibold rounded-lg hover:bg-indigo-600 focus:ring-4 focus:outline-none focus:ring-indigo-300 transition duration-300 ease-in-out text-center">
                    Add Student
                </a>
                <a href="issueBook.jsp" class="block px-8 py-4 bg-orange-500 text-white font-semibold rounded-lg hover:bg-orange-600 focus:ring-4 focus:outline-none focus:ring-orange-300 transition duration-300 ease-in-out text-center">
                    Issue Book
                </a>
                <a href="viewIssuedBooks.jsp" class="block px-8 py-4 bg-red-500 text-white font-semibold rounded-lg hover:bg-red-600 focus:ring-4 focus:outline-none focus:ring-red-300 transition duration-300 ease-in-out text-center">
                    View Issued Books
                </a>
            </div>
        </div>
    </section>
</div>

</body>
</html>
