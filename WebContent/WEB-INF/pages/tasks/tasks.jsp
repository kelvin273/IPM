
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li class="active">Tasks</li>
	</ul>

	<%@include file="../common/menu.jsp"%>
	<h1>${projectName} </h1>
	<h2>Tasks</h2>

	<a href="/IPM/tasks/newTask">New Task</a> <br />

	<div class="bs-component">
		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>Task Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
					Integer i = 0;
				%>
				<c:forEach items="${tasks}" var="r">
					<%
						i++;
					%>
					<tr>
						<td><c:out value="<%=i%>" /></td>
						<td><c:out value="${r.name}" /></td>
						<td><form:form method="POST"
								action="/IPM/tasks/removeTask" style="height:4px" data-confirm="Are you sure?">
								<input id="taskId" name="taskId" type="hidden"
									value="<c:out value="${r.id}"/>" />
								<input type="submit" value="Remove"
									class="btn btn-danger btn-xs" data-toggle="confirmation"/>
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<%@include file="../common/footer.jsp"%>