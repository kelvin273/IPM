
<%@include file="../common/header.jsp"%>



<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li class="active">Projects</li>
	</ul>

	<h1>Projects</h1>

	<a href="/IPM/projects/newProject"><span
		class="glyphicon glyphicon-plus"></span>New project</a>

	<div class="bs-component">
		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>Project Name</th>
					<th>Action</th>
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
						<td><form:form method="POST"
								action="/IPM/projects/updateProject" style="height:4px">
								<input id="projectId" name="projectId" type="hidden"
									value="<c:out value="${proj.id}"/>" />
								<input type="submit" value="${proj.name}"
									class="btn btn-success btn-xs" />
							</form:form></td>
						<td>
							<div style="overflow: hidden">
								<div style="float: left">
									<form:form method="POST" action="/IPM/skills/skills"
										style="height:4px">
										<input id="projectId" name="projectId" type="hidden"
											value="<c:out value="${proj.id}"/>" />
										<input id="projectName" name="projectName" type="hidden"
											value="<c:out value="${proj.name}"/>" />
										<button type="submit" class="btn btn-success btn-xs">
											<span class="glyphicon glyphicon-star-empty"></span>&nbsp;&nbsp;Skills
										</button>

									</form:form>
								</div>
								<div style="float: left">&nbsp;</div>
								<div style="float: left">
									<form:form method="POST" action="/IPM/resources/resources"
										style="height:4px">
										<input id="projectId" name="projectId" type="hidden"
											value="<c:out value="${proj.id}"/>" />
										<input id="projectName" name="projectName" type="hidden"
											value="<c:out value="${proj.name}"/>" />
										<span class=""></span>
										<button type="submit" class="btn btn-info btn-xs">
											<span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;Resources
										</button>
									</form:form>
								</div>
								<div style="float: left">&nbsp;</div>
								<div style="float: left">
									<form:form method="POST" action="/IPM/tasks/tasks"
										style="height:4px">
										<input id="projectId" name="projectId" type="hidden"
											value="<c:out value="${proj.id}"/>" />
										<input id="projectName" name="projectName" type="hidden"
											value="<c:out value="${proj.name}"/>" />
										<button type="submit" class="btn btn-warning btn-xs">
											<span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;Tasks
										</button>
									</form:form>
								</div>
								<div style="float: left">&nbsp;</div>
								<div style="float: left">
									<form:form method="POST" action="/IPM/plans/plans"
										style="height:4px">
										<input id="projectId" name="projectId" type="hidden"
											value="<c:out value="${proj.id}"/>" />
										<input id="projectName" name="projectName" type="hidden"
											value="<c:out value="${proj.name}"/>" />
										<button type="submit" class="btn btn-default btn-xs">
											<span class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;Plan
										</button>
									</form:form>
								</div>
								<div style="float: left">&nbsp;</div>
								<div style="float: right">
									<form:form method="POST" action="/IPM/projects/removeProject"
										data-confirm="Are you sure?" style="height:4px">
										<input id="projectId" name="projectId" type="hidden"
											value="<c:out value="${proj.id}"/>" />
										<button type="submit" class="btn btn-danger btn-xs"
											data-toggle="confirmation">
											<span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;Remove
										</button>
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
