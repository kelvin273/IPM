	<%@include file="../common/header.jsp"%>
	<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/resources/resources"/>">Resources</a></li>
		<li class="active">New Resource</li>
	</ul>

	<%@include file="../common/menu.jsp"%>
	<h1>New Resource</h1>
	<form:form method="POST" modelAttribute="resource"
		commandName="resource">
		<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
			cellpadding="5">
			<tr>
				<td><form:label path="name">Resource Name</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:label path="maxDedication">maxDedication</form:label></td>
				<td><form:input path="maxDedication" /></td>
				<td><form:label path="salary">salary</form:label></td>
				<td><form:input path="salary" /></td>
				<td><form:label path="skills">skills</form:label></td>
				<td><form:select multiple="true" path="skills">
						<form:options items="${skills}" itemValue="id" itemLabel="name" />
					</form:select></td>
				<form:errors path="name" cssclass="error"></form:errors>
				<form:errors path="maxDedication" cssclass="error"></form:errors>
				<form:errors path="salary" cssclass="error"></form:errors>
				<form:errors path="skills" cssclass="error"></form:errors>
			</tr>
		</table>
		<br>
		<input type="submit" align="center" value="Execute">
	</form:form>
	<%@include file="../common/footer.jsp"%>
