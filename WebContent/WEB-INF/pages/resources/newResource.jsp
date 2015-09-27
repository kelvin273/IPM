
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/resources/resources"/>">Resources</a></li>
		<li class="active">New Resource</li>
	</ul>
	<%@include file="../common/menu.jsp"%>
	<form:form method="POST" modelAttribute="resource"
		commandName="resource" class="form-horizontal">
		<fieldset>
			<legend>New Resource</legend>
			<c:set var="errors">
				<form:errors path="name" cssclass="error" />
				<form:errors path="maxDedication" cssclass="error"></form:errors>
				<form:errors path="salary" cssclass="error"></form:errors>
				<form:errors path="skills" cssclass="error"></form:errors>
			</c:set>
			<c:if test="${fn:length(errors)>0}">
				<div class="error"></div>
				<div class="alert alert-dismissible alert-danger">
					<strong><form:errors path="name" cssclass="error"></form:errors>
						<form:errors path="maxDedication" cssclass="error"></form:errors>
						<form:errors path="salary" cssclass="error"></form:errors> <form:errors
							path="skills" cssclass="error"></form:errors></strong>
				</div>
			</c:if>
			<div class="form-group">
				<form:label path="name" class="col-lg-2 control-label">Resource Name</form:label>
				<div class="col-lg-10">
					<form:input path="name" class="form-control" placeholder="Name" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="maxDedication" class="col-lg-2 control-label">Max Dedication</form:label>
				<div class="col-lg-10">
					<form:input path="maxDedication" class="form-control"
						placeholder="Max Dedication" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="salary" class="col-lg-2 control-label">Cost</form:label>
				<div class="col-lg-10">
					<form:input path="salary" class="form-control" placeholder="Cost" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="skills" class="col-lg-2 control-label">skills</form:label>
				<div class="col-lg-10">
					<form:select multiple="true" path="skills"
						form-control="form-control">
						<form:options items="${skills}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>
			</div>
			<input type="submit" align="center" value="Save"
				class="btn btn-primary">
	</form:form>
	<%@include file="../common/footer.jsp"%>