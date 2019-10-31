<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Online Banking | Error adding new account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/flatly/bootstrap.min.css"
        crossorigin="anonymous">
</head>

<body>

    <div class="container" style="margin-top: 6%">
        <!-- Content of each page is here -->
        <div class="row justify-content-center">
            <div class="col-md-5">

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title">Error adding new account</h4>
                    </div>
                    <div class="card-body">
                    	<div class="alert alert-danger">
                    	You have 3 accounts already. if you want to add another account then you must remove an existing account.
                		</div>
                    </div>
                    <div class="border-top card-body text-center">
                        <a class="text-primary" href="${contextPath}/accounts">Back to your accounts</a>
                    </div>
                </div>

            </div>
        </div>
    </div>


</body>

</html>