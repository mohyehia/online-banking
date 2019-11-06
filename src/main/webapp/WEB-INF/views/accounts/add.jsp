<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />

<div class="container">
        <!-- Content of each page is here -->
        <div class="row justify-content-center">
            <div class="col-md-5">
				<c:if test="${error != null}">
					<div class="alert alert-dismissible alert-danger">${error}</div>
				</c:if>
				<c:if test="${errors.size() > 0}">
					<div class="alert alert-dismissible alert-danger">
						<c:forEach items="${errors}" var="error">
	                		${error} <br />
	                	</c:forEach>
					</div>
				</c:if>

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title">Add new account</h4>
                    </div>
                    <div class="card-body">
                        <form:form method="POST" action="${contextPath}/accounts/add" modelAttribute="account">
							<div class="form-group">
                                <label for="accountType">Account Type</label>
                                <select id="accountType" name="accountType" class="form-control">
                                    <option> Choose...</option>
                                    <c:forEach var="accountType" items="${accountTypes}">
                                    	<option value="${accountType}">${accountType}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group d-none" id="creditDiv">
                                <label for="creditType">Credit Type</label>
                                <input type="text" id="creditType" name="creditType" class="form-control" placeholder="Credit Type" readonly>
                            	<select id="ASCreditType" class="form-control d-none">
                                    <option value="Credit">Credit</option>
                                    <option value="Debit">Debit</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="balance">Account Balance</label>
                                <input type="number" id="balance" name="balance" class="form-control" placeholder="Account Balance" required>
                            </div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                 
                            <div class="form-group">
                                <input type="submit" value="Save" class="btn btn-block btn-primary" />
                            </div>
                        </form:form>
                    </div>
                    <div class="border-top card-body text-center">
                        <a class="text-primary" href="${contextPath}/accounts">Back to your accounts</a>
                    </div>
                </div>

            </div>
        </div>
    </div>

<jsp:include page="../assets/footer.jsp" />
<script>
$(document).ready(function(){
	$('#accountType').change(function(){
		$('#creditDiv').removeClass('d-none');
		var accountType = $(this).val();
		switch(accountType){
		case 'AC':
			console.log('AC selected');
			$('#ASCreditType').addClass('d-none');
			$('#creditType').show();
			$('#creditType').val('Credit');
			break;
		case 'AT':
			console.log('AT selected');
			$('#ASCreditType').addClass('d-none');
			$('#creditType').show();
			$('#creditType').val('Debit');
			break;
		case 'AS':
			console.log('AS selected');
			$('#creditType').hide();
			$('#ASCreditType').removeClass('d-none');
			break;
		default:
			console.log('No account type selected!');
			$('#creditDiv').addClass('d-none');
			break;
		}
	});
});
</script>