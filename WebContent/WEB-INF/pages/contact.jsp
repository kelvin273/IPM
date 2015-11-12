<%@include file="common/header.jsp"%>
<%@include file="common/menu.jsp"%>

<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li class="active">Contact</li>
	</ul>

	<a href="/IPM/projects/newProject">New project</a> <a
		href="/IPM/projects/projects">Projects</a> <br /> <br />
		
		
		
		<form:form method="POST" modelAttribute="contact" commandName="contact"
		class="form-horizontal">
		<fieldset>
			<legend>New Project</legend>
			<c:set var="errors">
				<form:errors path="email" cssclass="error" />
			</c:set>
			<c:if test="${fn:length(errors)>0}">
				<div class="error"></div>
				<div class="alert alert-dismissible alert-danger">
					<strong><form:errors path="email" cssclass="error" /></strong>
				</div>
			</c:if>
			<div class="form-group">
				<form:label path="email" class="col-lg-2 control-label">Email</form:label>
				<div class="col-lg-10">
					<form:input path="email" class="form-control" placeholder="Email" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="message" class="col-lg-2 control-label">Message</form:label>
				<div class="col-lg-10">
					<form:textarea path="message" class="form-control" placeholder="Message" rows="3"/>
					<span class="help-block"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-10 col-lg-offset-2">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</div>
	</form:form>
	
	
</div>
<%@include file="common/footer.jsp"%>

