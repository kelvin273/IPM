<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<%@include file="../common/header.jsp"%>
	<h1>New Skill</h1>
	<%@include file="../common/menu.jsp"%>
	<br>
	<br>
	<form:form method="POST" modelAttribute="resource" commandName="resource">
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><form:label path="name">Resource Name</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:label path="maxDedication">maxDedication</form:label></td>
				<td><form:input path="maxDedication" /></td>
				<td><form:label path="salary">salary</form:label></td>
				<td><form:input path="salary"/></td>
				<form:errors path="name" cssclass="error"></form:errors>
			</tr>
		</table>
		<br>
		<input type="submit" align="center" value="Execute">
	</form:form>
	<%@include file="../common/footer.jsp"%>
</body>
</html>