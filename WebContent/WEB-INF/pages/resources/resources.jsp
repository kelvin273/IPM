
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li class="active">Resources</li>
	</ul>

	<%@include file="../common/menu.jsp"%>
	<h1>${projectName} </h1>
	<h2>Resources</h2>

	<a href="/IPM/resources/newResource">New Resource</a> <br />

	<div class="bs-component">
		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>#</th>
					<th>Resource Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
					Integer i = 0;
				%>
				<c:forEach items="${resources}" var="r">
					<%
						i++;
					%>
					<tr>
						<td><c:out value="<%=i%>" /></td>
						<td><form:form method="POST"
								action="/IPM/resources/updateResource" style="height:4px">
								<input id="resourceId" name="resourceId" type="hidden"
									value="<c:out value="${r.id}"/>" />
								<input type="submit" value="${r.name}"
									class="btn btn-success btn-xs" />
							</form:form></td>
						<td><form:form method="POST"
								action="/IPM/resources/removeResource" style="height:4px" data-confirm="Are you sure?">
								<input id="resourceId" name="resourceId" type="hidden"
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