<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../assets/header.jsp" />

<div class="row justify-content-center">
	<div class="col-md-5">
		<h3>Update Your Profile Info</h3>
		<ul class="nav nav-tabs" style="margin-bottom: 15px">
			<li class="nav-item">
				<a class="nav-link active" data-toggle="tab" id="profileLink" href="#profile">Profile</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-toggle="tab" id="passwordLink" href="#password">Password</a>
			</li>
		</ul>
		
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade active show" id="profile">
			
				<c:if test="${errors.size() > 0}">
					<div class="alert alert-dismissible alert-danger">
						<c:forEach items="${errors}" var="error">
	                		${error} <br />
	                	</c:forEach>
					</div>
				</c:if>
				<c:if test="${error != null}">
					<div class="alert alert-dismissible alert-danger">${error}</div>
				</c:if>
				<c:if test="${success != null}">
					<div class="alert alert-dismissible alert-success">${success}</div>
				</c:if>
				
				<form:form method="POST" id="profileForm" action="${contextPath}/me">
					<div class="form-group">
						<label>Email Address</label><br>
						<label><b>${user.email}</b></label>
					</div>
					<div class="form-group">
						<label for="firstName">First name</label>
						<input type="text" name="firstName" class="form-control" value="${user.firstName}"
							placeholder="Your first name" required>
					</div>
					<div class="form-group">
						<label for="lastName">Last name</label>
						<input type="text" class="form-control" name="lastName" value="${user.lastName}"
							placeholder="Your last name" required>
					</div>
					<div class="form-group">
						<label for="city">City</label> <select id="city" name="city" class="form-control">
							<option>Choose...</option>
							<option <c:if test = "${user.city == 'Cairo'}" >selected</c:if> >Cairo</option>
							<option <c:if test = "${user.city == 'Alex'}" >selected</c:if> >Alex</option>
							<option <c:if test = "${user.city == 'Giza'}" >selected</c:if> >Giza</option>
							<option <c:if test = "${user.city == 'Tanta'}" >selected</c:if> >Tanta</option>
						</select>
					</div>
					<div class="form-group">
						<label for="address">Address</label>
						<input type="text" id="address" name="address" class="form-control" value="${user.address}"
							placeholder="Address" required>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div class="form-group">
						<input type="submit" value="Save" class="btn btn-block btn-primary" />
					</div>
				</form:form>
			</div>
			<div class="tab-pane fade" id="password">
				<c:if test="${passwordErrors.size() > 0}">
					<div class="alert alert-dismissible alert-danger">
						<c:forEach items="${passwordErrors}" var="error">
	                		${error} <br />
	                	</c:forEach>
					</div>
				</c:if>
				
				<form:form method="post" id="changePasswordForm" action="${contextPath}/change-password">
					<div class="form-group">
						<label for="currentPassword">Current Password</label> 
						<input type="password" id="currentPassword" name="currentPassword" class="form-control" placeholder="Enter current password">
					</div>
					<div class="form-group">
						<label for="newPassword">New Password</label> 
						<input type="password" id="newPassword" name="newPassword" class="form-control" placeholder="Enter new password">
					</div>
					<div class="form-group">
						<label for="password">Confirm New Password</label> 
						<input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Enter confirmation password">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<div class="form-group">
						<input type="submit" value="Update Password" class="btn btn-block btn-primary" />
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../assets/footer.jsp" />

<script>
$(document).ready(function(){
	var url = window.location.href;
	if(url === 'http://localhost:8081/me#password'){
		var activeTab = url.substring(url.indexOf("#") + 1);
		console.log(activeTab);
		$('#profileLink').removeClass('active');
		$('#passwordLink').addClass('active');
		$(".tab-pane").removeClass("active show");
		$("#" + activeTab).addClass("active show");
	}
});
</script>