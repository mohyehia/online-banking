<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />

<div class="container">
        <!-- Content of each page is here -->
        <div class="row justify-content-center">
            <div class="col-md-6">

				<c:if test="${success != null}">
					<div class="alert alert-dismissible alert-success">${success}</div>
				</c:if>
				<c:if test="${error != null}">
					<div class="alert alert-dismissible alert-danger">${error}</div>
				</c:if>

                <div class="card border-light mb-3">
                    <div class="card-header">
                        <h4 class="card-title">Add new transaction</h4>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="${contextPath}/transactions/add">
							<div class="form-row">
								<div class=" col form-group">
	                                <label for="fromAccount">From Account</label>
	                                <select id="fromAccount" name="fromAccount" class="form-control">
	                                    <option> Choose...</option>
	                                    <option>Savings</option>
	                                    <option>Primary</option>
	                                </select>
                            	</div>
	                            <div class=" col form-group">
	                                <label for="fromAccountBalance">From account balance</label>
	                                <input type="text" id="fromAccountBalance" name="fromAccountBalance" class="form-control" placeholder="From account balance" readonly>
	                            </div>
							</div>
                            <div class="form-row">
                            	<div class="col form-group">
	                                <label for="toAccount">To Account</label>
	                                <select id="toAccount" name="toAccount" class="form-control">
	                                    <option> Choose...</option>
	                                    <option>Savings</option>
	                                    <option>Primary</option>
	                                </select>
                            	</div>
	                            <div class="col form-group">
	                                <label for="toAccountBalance">To account balance</label>
	                                <input type="text" id="toAccountBalance" name="toAccountBalance" class="form-control" placeholder="To account balance" readonly>
	                            </div>
                            </div>
                            <div class="form-group">
                                <label for="amount">Amount to transfer</label>   
                                <input type="number" id="amount" name="amount" class="form-control" placeholder="Amount to transfer" required>
                            </div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                 
                            <div class="form-group">
                                <input type="submit" value="Submit" class="btn btn-block btn-primary" />
                            </div>
                        </form>
                    </div>
                    <div class="border-top card-body text-center">
                        <a class="text-primary" href="${contextPath}/transactions">Back to your transactions</a>
                    </div>
                </div>

            </div>
        </div>
    </div>

<jsp:include page="../assets/footer.jsp" />