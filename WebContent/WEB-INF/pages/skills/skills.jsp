
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li class="active">Skills</li>
	</ul>
	<%@include file="../common/menu.jsp"%>
	<h1>${projectName}</h1>
	<h2>Skills</h2>

	<a href="/IPM/skills/newSkill"><span class="glyphicon glyphicon-star-empty"></span>New Skill</a> <br />

	<div class="bs-component">
		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>Skill Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
					Integer i = 0;
				%>
				<c:forEach items="${skills}" var="s">
					<%
						i++;
					%>
					<tr>
						<td><c:out value="<%=i%>" /></td>
						<td><form:form method="POST"
								action="/IPM/skills/updateSkill" style="height:4px">
								<input id="skillId" name="skillId" type="hidden"
									value="<c:out value="${s.id}"/>" />
								<input type="submit" value="${s.name}"
									class="btn btn-success btn-xs" />
							</form:form></td>
						<td>
						<form:form method="POST"
								action="/IPM/skills/removeSkill" style="height:4px" data-confirm="Are you sure?">
								<input id="skillId" name="skillId" type="hidden"
									value="<c:out value="${s.id}"/>" />
					<button type="submit" class="btn btn-danger btn-xs"
											data-toggle="confirmation">
											<span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;Remove
										</button>
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@include file="../common/footer.jsp"%>