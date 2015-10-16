
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/tasks/tasks"/>">Tasks</a></li>
		<li class="active">New Task</li>
	</ul>
	<%@include file="../common/menu.jsp"%>
	<h1>${projectName}</h1>
	<form:form method="POST" modelAttribute="task"
		commandName="task" class="form-horizontal">
		<fieldset>
			<legend>New Task</legend>
			<c:set var="errors">
				<form:errors path="name" cssclass="error" />
			</c:set>
			<c:if test="${fn:length(errors)>0}">
				<div class="error"></div>
				<div class="alert alert-dismissible alert-danger">
					<strong><form:errors path="name" cssclass="error"></form:errors>
					</strong>
				</div>
			</c:if>
			<div class="form-group">
				<form:label path="name" class="col-lg-2 control-label">Task Name</form:label>
				<div class="col-lg-10">
					<form:input path="name" class="form-control" placeholder="Name" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="effort" class="col-lg-2 control-label">Effort</form:label>
				<div class="col-lg-10">
					<form:input path="effort" class="form-control"
						placeholder="Effort" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="exclusive" class="col-lg-2 control-label">Exclusive</form:label>
				<div class="col-lg-10">
					<form:checkbox path="exclusive" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="requiredSkills" class="col-lg-2 control-label">Required skills</form:label>
				<div class="col-lg-10">
					<form:select multiple="true" path="requiredSkills"
 						form-control="form-control" class="form-control">
						<form:options items="${skills}" itemValue="id" itemLabel="name" /> 
					</form:select> 
				</div>
			</div>
			<div class="form-group">
				<form:label path="resources" class="col-lg-2 control-label">Fixed Resources</form:label>
				<div class="col-lg-10">
					<form:select multiple="true" path="resources"
 						form-control="form-control" class="form-control">
						<form:options items="${resources}" itemValue="id" itemLabel="name" /> 
					</form:select> 
				</div>
			</div>
			<div class="form-group">
				<form:label path="precedentTasks" class="col-lg-2 control-label">Precedent tasks</form:label>
				<div class="col-lg-10">
					<form:select multiple="true" path="precedentTasks"
 						form-control="form-control" class="form-control">
						<form:options items="${precedentTasks}" itemValue="id" itemLabel="name" /> 
					</form:select> 
				</div>
			</div>
			<input type="submit" align="center" value="Save"
				class="btn btn-primary">
	</form:form>
	<%@include file="../common/footer.jsp"%>