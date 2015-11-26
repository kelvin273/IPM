
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li class="active">Plans</li>
	</ul>

	<%@include file="../common/menu.jsp"%>
	<h1>${projectName} </h1>
	<h2>Plans</h2>

	<a href="/IPM/plans/newPlan"><span class="glyphicon glyphicon-time"></span>New Plan</a> <br />

	<div class="bs-component">
		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>Plan Name</th>
					<th>Go to</th>
				</tr>
			</thead>
			<tbody>
				<%
					Integer i = 0;
				%>
				<c:forEach items="${plans}" var="r">
					<%
						i++;
					%>
					<tr>
						<td><c:out value="<%=i%>" /></td>
						<td><c:out value="${r.name}" /></td>
						<td><form:form method="POST"
								action="/IPM/plans/removePlan" style="height:4px">
								<input id="PlanId" name="PlanId" type="hidden"
									value="<c:out value="${r.id}"/>" />
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