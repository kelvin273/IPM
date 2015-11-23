<div class="form-group">
		<form:form method="POST" modelAttribute="skill" commandName="skill" action="/IPM/skills/newSkill"
			class="form-horizontal">
			<form:hidden path="id" />
			<fieldset>
				<legend>Skill</legend>
				<c:if test="${success}">
				<div class="alert alert-dismissible alert-success">
					<strong>Skill updated!</strong>
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