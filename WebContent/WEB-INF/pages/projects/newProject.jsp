
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li class="active">New Project</li>
	</ul>
	<%@include file="../common/menu.jsp"%>
	<form:form method="POST" modelAttribute="project" commandName="project" action="/IPM/projects/newProject"
		class="form-horizontal">
		<fieldset>
			<legend>Project</legend>
			<c:if test="${success}">
				<div class="alert alert-dismissible alert-success">
					<strong>Project updated!</strong>
				</div>
			</c:if>
			<c:set var="errors">
				<form:errors path="name" cssclass="error" />
			</c:set>
			<c:if test="${fn:length(errors)>0}">
				<div class="error"></div>
				<div class="alert alert-dismissible alert-danger">
					<strong><form:errors path="name" cssclass="error" /></strong>
				</div>
			</c:if>
			<form:hidden path="id" />
			<div class="form-group">
				<form:label path="name" class="col-lg-2 control-label">Project Name</form:label>
				<div class="col-lg-10">
					<form:input path="name" class="form-control" placeholder="Name" />
				</div>
			</div>
			<input type="submit" align="center" value="Save"
				class="btn btn-primary">
	</form:form>

</div>
<%@include file="../common/footer.jsp"%>
