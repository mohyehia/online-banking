<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Online Banking | Create new account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/flatly/bootstrap.min.css"
        crossorigin="anonymous">
</head>

<body>

    <div class="container" style="margin-top: 3%">
        <!-- Content of each page is here -->
        <div class="row justify-content-center">
            <div class="col-md-6">

				<c:if test="${errors != null}">
					<div class="alert alert-dismissible alert-danger">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
                    	<c:forEach items="${errors}" var="error">
                    		${error.defaultMessage} <br />
                    	</c:forEach>
                	</div>
				</c:if>
				<c:if test="${err != null}">
					<div class="alert alert-dismissible alert-danger">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
						${err}
					</div>
				</c:if>

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title">Create new account</h4>
                    </div>
                    <div class="card-body">
                        <form:form action="${contextPath}/auth/signup" method="POST" modelAttribute="user">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" class="form-control"
                                    placeholder="Enter email address" required>
                            </div>
                            <div class="form-row">
                                <div class="col form-group">
                                    <label for="firstName">First name </label>   
                                        <input type="text" id="firstName" name="firstName" class="form-control" 
                                        placeholder="Your first name" required>
                                </div> <!-- form-group end.// -->
                                <div class="col form-group">
                                    <label for="lastName">Last name</label>
                                        <input type="text" class="form-control" name="lastName" id="lastName"
                                        placeholder="Your last name" required>
                                </div> <!-- form-group end.// -->
                            </div>
                            <div class="form-row">
                                <div class=" col form-group">
                                    <label for="city">City</label>
                                    <select id="city" name="city" class="form-control">
                                        <option> Choose...</option>
                                        <option>Cairo</option>
                                        <option>Alex</option>
                                        <option>Giza</option>
                                        <option>Tanta</option>
                                    </select>
                                </div>
                                <div class="col form-group">
                                    <label for="address">Address</label>   
                                        <input type="text" id="address" name="address" class="form-control" 
                                        placeholder="Address" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>   
                                    <input type="password" id="password" name="password" class="form-control" 
                                    placeholder="Password" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">Confirmation Password</label>
                                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                                    placeholder="Confirmation Password" required>
                            </div> 
                            <div class="form-group">
                                <input type="submit" value="Signup" class="btn btn-block btn-primary" />
                            </div>
                        </form:form>
                    </div>
                    <div class="border-top card-body text-center">
                        Already have account? <a class="text-primary" href="${pageContext.request.contextPath}/auth/login">Login here</a>
                    </div>
                </div>

            </div>
        </div>
    </div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
</body>

</html>