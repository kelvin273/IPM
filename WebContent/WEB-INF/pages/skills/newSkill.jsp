<%@include file="../common/header.jsp"%>
<%@include file="../common/menu.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/skills/skills"/>">Skills</a></li>
		<li class="active">New Skills</li>
	</ul>
	<h1>${projectName} </h1>
	<div class="form-group">
		<form:form method="POST" modelAttribute="skill" commandName="skill"
			class="form-horizontal">
			<fieldset>
				<legend>New Skill</legend>
				<c:set var="errors">
					<form:errors path="name" cssclass="error" />
				</c:set>
				<c:if test="${fn:length(errors)>0}">
					<div class="error"></div>
					<div class="alert alert-dismissible alert-danger">
						<strong><form:errors path="name" cssclass="error" /></strong>
					</div>
				</c:if>
				<form:label path="name">Skill Name</form:label>
				<div class="form-group">
					<form:label path="name" class="col-lg-2 control-label">Skill Name</form:label>
					<div class="col-lg-10">
						<form:input path="name" class="form-control" placeholder="Name" />
					</div>
				</div>
				<input type="submit" align="center" value="Save"
					class="btn btn-primary">
		</form:form>
	</div>
	<%@include file="../common/footer.jsp"%>