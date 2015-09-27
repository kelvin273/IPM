<%@include file="./common/header.jsp"%>
<%@page session="true"%>

<body onload='document.loginForm.username.focus();'>

	<div class="col-lg-6">
		<form name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST'
			class="form-horizontal">

			<fieldset>
				<legend>Login</legend>
				<c:if test="${not empty error}">
					<div class="error"></div>
					<div class="alert alert-dismissible alert-danger">
						<strong>${error}</strong>
					</div>
					<br />
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
					<br />
				</c:if>
				<div class="form-group">
					<label for="username" class="col-lg-2 control-label">Username</label>
					<div class="col-lg-10">
						<input class="form-control" id="username" name="username"
							placeholder="Username" type="text" value="alex">
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="col-lg-2 control-label">Password</label>
					<div class="col-lg-10">
						<input class="form-control" id="password" name="password"
							placeholder="Password" type="password" value="123456">
					</div>
				</div>
			</fieldset>
			<button type="submit" class="btn btn-primary">Login</button>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>

	<%@include file="./common/footer.jsp"%>