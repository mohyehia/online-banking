<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="../assets/header.jsp" />

<div class="row justify-content-center">
	<div class="col-md-5">
		<h3>Update Your Profile Info</h3>
		<ul class="nav nav-tabs" style="margin-bottom: 15px">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#password">Password</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade active show" id="profile">
				<form>
					<div class="form-group">
						<label for="firstName">First name </label> <input type="text"
							id="firstName" name="firstName" class="form-control"
							placeholder="Your first name" required>
					</div>
					<div class="form-group">
						<label for="lastName">Last name</label> <input type="text"
							class="form-control" name="lastName" id="lastName"
							placeholder="Your last name" required>
					</div>
					<div class="form-group">
						<label for="city">City</label> <select id="city" name="city"
							class="form-control">
							<option>Choose...</option>
							<option>Cairo</option>
							<option>Alex</option>
							<option>Giza</option>
							<option>Tanta</option>
						</select>
					</div>
					<div class="form-group">
						<label for="address">Address</label> <input type="text"
							id="address" name="address" class="form-control"
							placeholder="Address" required>
					</div>
					<div class="form-group">
						<input type="submit" value="Save"
							class="btn btn-block btn-primary" />
					</div>
				</form>
			</div>
			<div class="tab-pane fade" id="password">
				<form>
					<div class="form-group">
						<label for="password">Current Password</label> <input
							type="password" id="password" name="password"
							class="form-control" placeholder="Enter password" required>
					</div>
					<div class="form-group">
						<label for="password">New Password</label> <input type="password"
							id="password" name="password" class="form-control"
							placeholder="Enter password" required>
					</div>
					<div class="form-group">
						<label for="password">Confirm New Password</label> <input
							type="password" id="password" name="password"
							class="form-control" placeholder="Enter password" required>
					</div>
					<div class="form-group">
						<input type="submit" value="Save"
							class="btn btn-block btn-primary" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../assets/footer.jsp" />