<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />

<div class="row">
	<div class="col-md-6">
		<h4>List of your Accounts</h4>
	</div>
	<div class="col-md-6 text-right">
		<c:if test="${accountsSize < 3}">
		<a class="btn btn-primary" href="${contextPath}/accounts/add">Add New Account</a>
		</c:if>
	</div>
</div>

<table class="table table-hover text-center" style="margin-top: 15px">
	<thead>
		<tr class="table-active">
			<th scope="col">#</th>
			<th scope="col">Account Type</th>
			<th scope="col">Credit Type</th>
			<th scope="col">Created</th>
			<th scope="col">Balance</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="account" items="${accounts}">
			<tr>
				<th scope="row">${account.id}</th>
				<td>${account.accountType}</td>
				<td>${account.creditType}</td>
				<td>${account.created}</td>
				<td>${account.balance} EGP</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="../assets/footer.jsp" />