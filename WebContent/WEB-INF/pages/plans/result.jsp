
<%@include file="../common/header.jsp"%>

<%@ page import="java.util.Date"%>
<%@ page import="com.adsf.ipm.ws.dto.task.TaskSolution"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<link rel="stylesheet" href="css/style.css">


<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/plans/plans"/>">Plans</a></li>
		<li class="active">Result</li>
	</ul>
	<%@include file="../common/menu.jsp"%>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Plan</h3>
		</div>
		<div class="panel-body">


			<h1>${projectName}</h1>

			<div id="container">
				<div class="svg"></div>
				<div id="tag"></div>
			</div>
			<h4>
				Total Cost:
				<fmt:formatNumber type="number" maxFractionDigits="2"
					value="${plan.projectCost}" />
				&nbsp; Total Time:
				<fmt:formatNumber type="number" maxFractionDigits="2"
					value="${plan.projectEnd}" />
			</h4>

			<h4>Breakdown Structure:</h4>
</br>
			<c:forEach items="${plan.tasks.task}" var="task">
				<h4><b>${task.id}</b></h4>
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th>Resource</th>
							<th>Participation</th>
						</tr>
					</thead>
					<tbody>



						<c:set var="total" value="${task.taskEnd + task.taskStart}"/>
								Total time: <fmt:formatNumber type="number" maxFractionDigits="2"
						 					value="${total}" /> 
						<%-- 		task End  <fmt:formatNumber type="number" maxFractionDigits="2" --%>
						<%-- 					value="${task.taskEnd}" /> --%>
						<c:forEach items="${task.resources.resource}" var="resource">
							<tr>
								<td>${resource.id}</td>
								<td><fmt:formatNumber type="number" maxFractionDigits="2"
										value="${resource.participation}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>

			<br /> <br /> <input type="submit" value="Save"
				class="btn btn-primary " /> <input type="submit" value="Share"
				class="btn btn-info " /> <br /> <br />




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

		</div>

	</div>
	Debug: ${plan.tasks.task}
</div>
<%@include file="../common/footer.jsp"%>