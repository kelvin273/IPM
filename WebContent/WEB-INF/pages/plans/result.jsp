
<%@include file="../common/header.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/plans/plans"/>">Plans</a></li>
		<li class="active">Result</li>
	</ul>
	<%@include file="../common/menu.jsp"%>
	<h1>${projectName}</h1>
	Cost: ${plan.projectCost}
	End: ${plan.projectEnd}
	<br/>
	<c:forEach items="${plan.tasks.task}" var="task">
		<h4>${task.id}</h4>
		task Start ${task.taskStart}
		task End  ${task.taskEnd}
		<br/>
		Resources: 
		<c:forEach items="${task.resources.resource}" var="resource">
			${resource.id} ${resource.participation}
		</c:forEach>
		<br/>
	</c:forEach>
	<br/>
	<br/>
	save this plan <br/>
	share this plan<br/>
	<br/><br/>
	Debug:
	${plan.tasks.task}
</div>
	<%@include file="../common/footer.jsp"%>