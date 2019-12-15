<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />
        <!-- Content of each page is here -->
        <div class="row justify-content-center">
            <div class="col-md-5">

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title">Error adding new account</h4>
                    </div>
                    <div class="card-body" >
                    	<div class="alert alert-danger">
                    	<p class="lead">You already have 3 accounts. if you want to add another account then you must remove an existing account.</p>
                		</div>
                    </div>
                    <div class="border-top card-body text-center">
                        <a class="text-primary" href="${contextPath}/accounts">Back to your accounts</a>
                    </div>
                </div>

            </div>
        </div>

<jsp:include page="../assets/footer.jsp" />