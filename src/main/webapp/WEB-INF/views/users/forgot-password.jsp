<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><spring:message code="FORGOT_PASSWORD_TITLE" /></title>
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
                    	${error}
                	</div>
				</c:if>
	

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title"><spring:message code="FORGOT_PASSWORD_FORM_TITLE" /></h4>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="${contextPath}/auth/forgot-password">
                            <div class="form-group">
                                <label for="email"><spring:message code="FORGOT_PASSWORD_EMAIL" /></label>
								<input type="email" id="email" name="email" class="form-control" placeholder="Email Address" autofocus>
							</div> 
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                 
                            <div class="form-group">
                                <input type="submit" value="<spring:message code="FORGOT_PASSWORD_FORM_BUTTON" />" class="btn btn-block btn-primary" />
                            </div>
                        </form>
                    </div>
                    <div class="border-top card-body text-center">
                        <spring:message code="FORGOT_PASSWORD_REMEMBER_CREDENTIALS" /> <a class="text-primary" href="${contextPath}/auth/login"><spring:message code="FORGOT_PASSWORD_LINK_TO_LOGIN" /></a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>