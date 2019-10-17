<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />

<div class="row">
	<div class="col-md-6">
		<h4>List of your Transactions</h4>
	</div>
	<div class="col-md-6 text-right">
		<a class="btn btn-primary" href="${contextPath}/transactions/add">Add New Transaction</a>
	</div>
</div>

<table class="table table-hover text-center" style="margin-top: 15px">
	<thead>
		<tr class="table-active">
			<th scope="col">Type</th>
			<th scope="col">Column heading</th>
			<th scope="col">Column heading</th>
			<th scope="col">Column heading</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th scope="row">Default</th>
			<td>Column content</td>
			<td>Column content</td>
			<td>Column content</td>
		</tr>
		<tr>
			<th scope="row">Default</th>
			<td>Column content</td>
			<td>Column content</td>
			<td>Column content</td>
		</tr>
		<tr>
			<th scope="row">Default</th>
			<td>Column content</td>
			<td>Column content</td>
			<td>Column content</td>
		</tr>
	</tbody>
</table>

<jsp:include page="../assets/footer.jsp" />