
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/plans/plans"/>">Plans</a></li>
		<li class="active">New Plan</li>
	</ul>
	<%@include file="../common/menu.jsp"%>
	<h1>${projectName}</h1>
	<form:form method="POST" modelAttribute="plan"
		commandName="plan" class="form-horizontal">
		<fieldset>
			<legend>New Plan</legend>
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
				<form:label path="name" class="col-lg-2 control-label">Plan Name</form:label>
				<div class="col-lg-10">
					<form:input path="name" class="form-control" placeholder="Name" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="tasks" class="col-lg-2 control-label">Tasks</form:label>
				<div class="col-lg-10">
					<form:select multiple="true" path="tasks" class="form-control" 
						form-control="form-control">
						<form:options items="${tasks}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<form:label path="resources" class="col-lg-2 control-label">Resources</form:label>
				<div class="col-lg-10">
					<form:select multiple="true" path="resources" class="form-control" 
						form-control="form-control">
						<form:options items="${resources}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>
			</div>

			<input type="submit" align="center" value="Get Plan"
				class="btn btn-primary">
	</form:form>
	<%@include file="../common/footer.jsp"%>