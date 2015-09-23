<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
<%@include file="common/header.jsp" %>
	<h1>New Project</h1>

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
			<%@include file="common/menu.jsp" %>
			<br>
			<br>
			<form:form method="POST" modelAttribute="project" commandName="project">
				<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
					cellpadding="5">
					<tr>
						<td><form:label path="name">Project Name</form:label></td>
						<td><form:input path="name"/></td>
						<form:errors path="name" cssclass="error"></form:errors>
					</tr>
				</table>
				<br>
				<input type="submit" align="center" value="Execute">
			</form:form>
		</c:if>

	</sec:authorize>

<%@include file="common/footer.jsp" %>
</body>
</html>