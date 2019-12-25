<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />

<div class="row">
	<div class="col-md-6">
		<h4><spring:message code="TRANSACTIONS_LIST" /></h4>
	</div>
	<div class="col-md-6 text-right">
		<a class="btn btn-primary" href="${contextPath}/transactions/add"><spring:message code="TRANSACTIONS_ADD_BUTTON" /></a>
	</div>
</div>

<table class="table table-hover text-center" style="margin-top: 15px">
	<c:if test="${success != null}">
		<div class="alert alert-dismissible alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			${success}
		</div>
	</c:if>
	<thead>
		<tr class="table-active">
			<th scope="col"><spring:message code="TRANSACTIONS_HEADER_NUMBER" /></th>
			<th scope="col"><spring:message code="TRANSACTIONS_HEADER_FROM" /></th>
			<th scope="col"><spring:message code="TRANSACTIONS_HEADER_TO" /></th>
			<th scope="col"><spring:message code="TRANSACTIONS_HEADER_CREATED" /></th>
			<th scope="col"><spring:message code="TRANSACTIONS_HEADER_AMOUNT" /></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<c:forEach var="transaction" items="${transactions}">
				<th scope="row">${transaction.id}</th>
				<td>${transaction.fromAccountId}</td>
				<td>${transaction.toAccountId}</td>
				<td>${transaction.created}</td>
				<td>${transaction.amount}</td>
			</c:forEach>
		</tr>
	</tbody>
</table>

<jsp:include page="../assets/footer.jsp" />
<script>
	$(function(){
		// change nav item class active
		$(".nav-item").removeClass('active');
	    $("#transactionsNavID").addClass('active');
	})
</script>