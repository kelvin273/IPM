
<%@include file="../common/header.jsp"%>

<%@ page import="java.util.Date"%>
<%@ page import="com.adsf.ipm.ws.dto.task.TaskSolution"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<script src="../js/dhtmlxgantt.js" type="text/javascript"
	charset="utf-8"></script>
<link rel="stylesheet" href="../css/dhtmlxgantt.css" type="text/css"
	media="screen" title="no title" charset="utf-8">

<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/plans/plans"/>">Plans</a></li>
		<li class="active">Result</li>
	</ul>
	<%@include file="../common/menu.jsp"%>

			<h1>${projectName}</h1>

			<div id="gantt_here" style='width: 100%; height: 40%;'></div>
			<script type="text/javascript">
        var tasks =  {
            data:[
             <%int i=1;%>
			<c:forEach items="${plan.tasks.task}" var="task">  
				<% TaskSolution taskSolution = (TaskSolution) pageContext.getAttribute("task");%>
				<c:set var="today" value="<%=new Date(new Date().getTime() + (60*60*24*1000) * (int)taskSolution.getTaskStart()+1 )%>"/>
			 	{id:<%=i++%>, text:"<%=taskSolution.getId()%>", start_date:"<fmt:formatDate type="date" value="${today}" pattern="dd-MM-yyyy"/>", duration:<%=((int) taskSolution.getTaskEnd() - (int) taskSolution
						.getTaskStart())%>, order:10,
                 progress:0.0, open: true},
			</c:forEach>
               
//                 {id:2, text:"Task #1", 	  start_date:"02-04-2013", duration:8, order:10,
//                     progress:0.6, parent:1},
               
            ],
                    links:[
//             { id:1, source:1, target:2, type:"0"},
        ]
        };

//         gantt.config.readonly = true;
		gantt.init("gantt_here");
		

		gantt.parse(tasks);

	</script>


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
				<h4>
					<b>${task.id}</b>
				</h4>
				<table class="table table-striped table-hover ">
					<thead>
						<tr>
							<th>Resource</th>
							<th>Participation</th>
						</tr>
					</thead>
					<tbody>



						<c:set var="total" value="${task.taskEnd + task.taskStart}" />
						Total time:
						<fmt:formatNumber type="number" maxFractionDigits="2"
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

	Debug: ${plan.tasks.task}
</div>
<%@include file="../common/footer.jsp"%>