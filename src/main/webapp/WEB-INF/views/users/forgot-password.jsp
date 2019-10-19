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
    <title>Online Banking | Please sign in</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/flatly/bootstrap.min.css"
        crossorigin="anonymous">
</head>

<body>

    <div class="container" style="margin-top: 6%">
        <!-- Content of each page is here -->
        <div class="row justify-content-center">
            <div class="col-md-5">

				<c:if test="${success != null}">
					<div class="alert alert-dismissible alert-success">${success}</div>
				</c:if>
				
				<c:if test="${error != null}">
					<div class="alert alert-dismissible alert-danger">
                    	Invalid username or password.
                	</div>
				</c:if>
	

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title">Enter your email address and we will send you a link to reset your password</h4>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="${contextPath}/auth/forgot-password">
                            <div class="form-group">
                                <label for="email">Email</label>
								<input type="email" id="email" name="email" class="form-control" placeholder="Email Address" required autofocus>
							</div> 
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                 
                            <div class="form-group">
                                <input type="submit" value="Submit" class="btn btn-block btn-primary" />
                            </div>
                        </form>
                    </div>
                    <div class="border-top card-body text-center">
                        Remember your credentials? <a class="text-primary" href="${contextPath}/auth/login">Login here</a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>