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

<div class="row" style="margin-top: 15px">
	<div class="col-md-12">
		<c:if test="${success != null}">
			<div class="alert alert-dismissible alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
					${success}
			</div>
		</c:if>
		<%--show success message after closing an account--%>
		<div class="alert alert-dismissible alert-success" id="accountClosed">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<span></span>
		</div>
	</div>
	<div class="col-md-12 table-responsive">
		<c:if test="${accounts.size() != 0}">
			<table class="table table-hover text-center">
				<thead>
				<tr class="table-active">
					<th scope="col"><spring:message code="ACCOUNTS_HEADER_NUMBER" /></th>
					<th scope="col"><spring:message code="ACCOUNTS_HEADER_TYPE" /></th>
					<th scope="col"><spring:message code="ACCOUNTS_HEADER_CREDIT_TYPE" /></th>
					<th scope="col"><spring:message code="ACCOUNTS_HEADER_CREATED" /></th>
					<th scope="col"><spring:message code="ACCOUNTS_HEADER_BALANCE" /></th>
					<th scope="col"><spring:message code="ACCOUNTS_HEADER_CLOSE" /></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<th scope="row">${account.id}</th>
						<td>${account.accountType}</td>
						<td>${account.creditType == '1' ? 'Debit' : 'Credit'}</td>
						<td>${account.created}</td>
						<td>${account.balance} EGP</td>
						<td>
							<c:if test="${account.active == true}">
								<button class="btn btn-sm btn-danger closeAccountBtn" id="${account.id}"><i class="fa fa-trash" aria-hidden="true"></i></button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>

<jsp:include page="../assets/footer.jsp" />
<!-- Modal code for closing an account -->
<div class="modal fade" id="closeAccountModal" >
	<div class="modal-dialog"role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Close Account</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger hidden" id="errorClosingAccount"></div>
				<p><strong>Are you sure you want to close this account?</strong></p>
				<span>By closing this account you will no longer be able to transfer money from and to this account.</span>
				<br>
				<span><strong>continue with this action?</strong></span>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" id="closeAccountBtn">Close</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	// change nav item class active
	$(".nav-item").removeClass('active');
    $("#accountsNavID").addClass('active');

    $('#accountClosed').css('display', 'none');
	$('#errorClosingAccount').css('display', 'none');

	// add csrf token to ajax request
	var csrf_token = $('meta[name="_csrf"]').attr('content');
	$.ajaxSetup({
		headers:
				{'X-CSRF-TOKEN': csrf_token}
	});

	$('.closeAccountBtn').click(function(){
		$('#errorClosingAccount').css('display', 'none');
		closeAccount($(this).attr('id'));
	});
	
	function closeAccount(accountId){
		$('#closeAccountModal').modal('show');
		$('#closeAccountBtn').data('accountId', accountId);
	};

	$('#closeAccountBtn').click(function () {
		let accountId = $(this).data('accountId');
		let successMsg = '<spring:message code="ACCOUNT_CLOSED_SUCCESSFULLY" />';
		// send ajax request to close this account
		$.ajax({
			url : "${contextPath}/accounts/close",
			method : "POST",
			data : {"accountId" : accountId},
			beforeSend : function () {
				console.log('Sending request!');
			},
			success : function (data) {
				if(data !== 'success'){
					$('#errorClosingAccount').css('display', 'block');
					$('#errorClosingAccount').html(data);
				}else{
					$('#errorClosingAccount').addClass('hidden');
					$('#accountClosed').css('display', 'block');
					$('#accountClosed>span').html(successMsg);
					$('#closeAccountModal').modal('hide');
				}
			},
			error: function (xhr, textStatus, errorThrown) {
				if (xhr.status === 403) {
					alert("Session Expired, Please Log in again");
					window.location = "${contextPath}/auth/login";
				} else {
					alert('There was an error handling your request');
				}
			}
		});
	});
});
</script>