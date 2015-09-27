
<%@include file="../common/header.jsp"%>

<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li class="active">Projects</li>
	</ul>

	<%@include file="../common/menu.jsp"%>
	<h1>Projects</h1>

	<a href="/IPM/projects/newProject">New project</a>

	<div class="bs-component">
		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>Project Name</th>
					<th>Go to</th>
				</tr>
			</thead>
			<tbody>
				<%
					Integer i = 0;
				%>
				<c:forEach items="${projects}" var="proj">
					<%
						i++;
					%>
					<tr>
						<td><c:out value="<%=i%>" /></td>
						<td><c:out value="${proj.name}" /></td>
						<td>
							<div style="overflow: hidden">
								<div style="float: left">
									<form:form method="POST" action="/IPM/skills/skills"
										style="height:4px">
										<input id="projectId" name="projectId" type="hidden"
											value="<c:out value="${proj.id}"/>" />
										<input type="submit" value="skills"
											class="btn btn-success btn-xs" />
									</form:form>
								</div>
								<div style="float: left">&nbsp;</div>
								<div style="float: left">
									<form:form method="POST" action="/IPM/resources/resources"
										style="height:4px">
										<input id="projectId" name="projectId" type="hidden"
											value="<c:out value="${proj.id}"/>" />
										<input type="submit" value="resources"
											class="btn btn-info btn-xs" />
									</form:form>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@include file="../common/footer.jsp"%>
