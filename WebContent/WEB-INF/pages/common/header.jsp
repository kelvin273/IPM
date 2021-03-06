<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>IPM</title>

<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
<script src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/docs.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap-confirmation.min.js"/>"></script>

</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="<c:url value="/"/>" class="navbar-brand"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;&nbsp;IPM</a>
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">

				</ul>

				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="hasRole('ROLE_USER')">
						<div hidden>
							<!-- For login user -->
							<c:url value="/j_spring_security_logout" var="logoutUrl" />
							<form action="${logoutUrl}" method="post" id="logoutForm">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>

							<script>
								function formSubmit() {
									document.getElementById("logoutForm")
											.submit();
								}
							</script>
						</div>
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#" id="download">${pageContext.request.userPrincipal.name}
									<span class="caret"></span>
									<ul class="dropdown-menu" aria-labelledby="download">
										<li><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;&nbsp;&nbsp;Logout</a></li>
									</ul></li>
						</c:if>
					</sec:authorize>
					<c:if test="${pageContext.request.userPrincipal.name == null}">
						<li><a href="<c:url value="/login"/>">Login</a></li>
					</c:if>
				</ul>

			</div>
		</div>
	</div>
	<BR />
	<BR />
	<BR />
	<BR />
