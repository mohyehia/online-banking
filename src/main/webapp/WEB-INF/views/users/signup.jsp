<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><spring:message code="SIGNUP_TITLE" /></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/flatly/bootstrap.min.css"
        crossorigin="anonymous">
</head>

<body>

    <div class="container" style="margin-top: 3%">
        <!-- Content of each page is here -->
        <div class="row justify-content-center">
            <div class="col-md-6">

				<c:if test="${err != null}">
					<div class="alert alert-dismissible alert-danger">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
						${err}
					</div>
				</c:if>

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title"><spring:message code="SIGNUP_FORM_TITLE" /></h4>
                    </div>
                    <div class="card-body">
                        <form:form action="${contextPath}/auth/signup" method="POST" modelAttribute="user">
                            <div class="form-group">
                                <label for="email"><spring:message code="SIGNUP_EMAIL" /></label>
                                <form:input path="email" id="email" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                    placeholder="Enter email address" />
                                <form:errors cssClass="text-danger" path="email" />
                            </div>
                            <div class="form-row">
                                <div class="col form-group">
                                    <label for="firstName"><spring:message code="SIGNUP_FIRST_NAME" /></label>   
                                        <form:input path="firstName" id="firstName" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                        placeholder="Your first name" />
                                        <form:errors cssClass="text-danger" path="firstName" />
                                </div>
                                <div class="col form-group">
                                    <label for="lastName"><spring:message code="SIGNUP_LAST_NAME" /></label>
                                        <form:input path="lastName" cssClass="form-control" id="lastName" cssErrorClass="form-control is-invalid"
                                        placeholder="Your last name" />
                                        <form:errors cssClass="text-danger" path="lastName" />
                                </div> 
                            </div>
                            <div class="form-row">
                                <div class="col form-group">
                                    <label for="city"><spring:message code="SIGNUP_CITY" /></label>
                                    <form:select id="city" path="city" cssClass="form-control" items="${cities}" cssErrorClass="form-control is-invalid">
                                    </form:select>
                                    <form:errors cssClass="text-danger" path="city" />
                                </div>
                                <div class="col form-group">
                                    <label for="address"><spring:message code="SIGNUP_ADDRESS" /></label>   
                                        <form:input path="address" id="address" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                        placeholder="Address" />
                                        <form:errors cssClass="text-danger" path="address" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password"><spring:message code="SIGNUP_PASSWORD" /></label>   
                                    <form:input type="password" path="password" id="password" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                    placeholder="Password" />
                                    <form:errors cssClass="text-danger" path="password" />
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword"><spring:message code="SIGNUP_CONFIRM_PASSWORD" /></label>
                                    <form:input type="password" path="confirmPassword" cssClass="form-control" id="confirmPassword" cssErrorClass="form-control is-invalid"
                                    placeholder="Confirmation Password" />
                                    <form:errors cssClass="text-danger" path="confirmPassword" />
                            </div> 
                            <div class="form-group">
                                <input type="submit" value="<spring:message code="SIGNUP_FORM_BUTTON" />" class="btn btn-block btn-primary" />
                            </div>
                        </form:form>
                    </div>
                    <div class="border-top card-body text-center">
                        <spring:message code="SIGNUP_HAVE_ACCOUNT" /> <a class="text-primary" href="${pageContext.request.contextPath}/auth/login"><spring:message code="SIGNUP_LINK_TO_LOGIN" /></a>
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