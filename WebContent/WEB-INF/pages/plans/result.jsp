
<%@include file="../common/header.jsp"%>

<%@ page import="java.util.Date" %>
<%@ page import="com.adsf.ipm.ws.dto.task.TaskSolution" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<link rel="stylesheet" href="css/style.css">


<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/plans/plans"/>">Plans</a></li>
		<li class="active">Result</li>
	</ul>
	<%@include file="../common/menu.jsp"%>
	<h1>${projectName}</h1>
	Cost: ${plan.projectCost} End: ${plan.projectEnd} <br />
	<c:forEach items="${plan.tasks.task}" var="task">
		<h4>${task.id}</h4>
		task Start ${task.taskStart}
		task End  ${task.taskEnd}
		<br />
		Resources: 
		<c:forEach items="${task.resources.resource}" var="resource">
			${resource.id} ${resource.participation}
		</c:forEach>
		<br />
	</c:forEach>
	<br /> <br /> save this plan <br /> share this plan<br /> <br /> <br />



	<div id="container">
		<div class="svg"></div>
		<div id="tag"></div>
	</div>
	<script src='/IPM/js/d3.min.js'></script>

	<script>
		var taskArray = [ 
		
		<c:forEach items="${plan.tasks.task}" var="task">
			{
				<% TaskSolution taskSolution = (TaskSolution) pageContext.getAttribute("task");%>
				task : "<%=taskSolution.getId()%>",
				type : "Tasks",
				<c:set var="today" value="<%=new Date(new Date().getTime() + (60*60*24*1000) * (int)taskSolution.getTaskStart()+1 )%>"/>
				<c:set var="end" value="<%=new Date(new Date().getTime() + (60*60*24*1000) * (int)taskSolution.getTaskEnd()+1 )%>"/>
				startTime : "<fmt:formatDate type="date" value="${today}" pattern="yyyy-MM-dd"/>", //year/month/day
				endTime : "<fmt:formatDate type="date" value="${end}" pattern="yyyy-MM-dd"/>", //year/month/day
				details : "This actually didn't take any conceptualization"
			},
		</c:forEach>

		];
	</script>
	<script src="/IPM/js/index.js"></script>
	Debug: ${plan.tasks.task}
</div>
<%@include file="../common/footer.jsp"%>