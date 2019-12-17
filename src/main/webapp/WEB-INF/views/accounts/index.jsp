<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />

<div class="row">
	<div class="col-md-6">
		<h4><spring:message code="ACCOUNTS_LIST" /></h4>
	</div>
	<div class="col-md-6 text-right">
		<c:if test="${accountsSize < 3}">
		<a class="btn btn-primary" href="${contextPath}/accounts/add"><spring:message code="ACCOUNTS_ADD_BUTTON" /></a>
		</c:if>
	</div>
</div>

<table class="table table-hover text-center" style="margin-top: 15px">
	<c:if test="${success != null}">
		<div class="alert alert-dismissible alert-success">${success}</div>
	</c:if>
	<thead>
		<tr class="table-active">
			<th scope="col">#</th>
			<th scope="col"><spring:message code="ACCOUNTS_HEADER_TYPE" /></th>
			<th scope="col"><spring:message code="ACCOUNTS_HEADER_CREDIT_TYPE" /></th>
			<th scope="col"><spring:message code="ACCOUNTS_HEADER_CREATED" /></th>
			<th scope="col"><spring:message code="ACCOUNTS_HEADER_BALANCE" /></th>
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