<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<%@include file="../common/header.jsp"%>
	<h1>New Project</h1>
	<%@include file="../common/menu.jsp"%>
	<br>
	<br>
	<form:form method="POST" modelAttribute="project" commandName="project">
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><form:label path="name">Project Name</form:label></td>
				<td><form:input path="name" /></td>
				<form:errors path="name" cssclass="error"></form:errors>
			</tr>
		</table>
		<br>
		<input type="submit" align="center" value="Execute">
	</form:form>
	<%@include file="../common/footer.jsp"%>
</body>
</html>