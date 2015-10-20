
<%@page import="com.adsf.ipm.ws.dto.resource.ResourceTaskPlanSolution"%>
<%@include file="../common/header.jsp"%>

<%@ page import="java.util.Date"%>
<%@ page import="com.adsf.ipm.ws.dto.task.TaskSolution"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<script src="../js/dhtmlxgantt.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../js/dhtmlxgantt_tooltip.js"></script>
<link rel="stylesheet" href="../css/dhtmlxgantt.css" type="text/css"
	media="screen" title="no title" charset="utf-8">

<style>
.weekend {
	background: #D7D6EF !important;
}
</style>


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
             <%int i=1;
             DecimalFormat df = new DecimalFormat("####0.00");
             %>
			<c:forEach items="${plan.tasks.task}" var="task">  
				<% TaskSolution taskSolution = (TaskSolution) pageContext.getAttribute("task");
				String participation ="";
				for(ResourceTaskPlanSolution rtp :taskSolution.getResources().getResource()){
					participation += "<br/> "+rtp.getId()+ "("+df.format(rtp.getParticipation())+")";
				}
				%>
				<c:set var="today" value="<%=new Date(new Date().getTime() + (60*60*24*1000) * (int)taskSolution.getTaskStart()+1 )%>"/>
			 	{
			 		<% float duration = Float.valueOf(df.format((float) taskSolution.getTaskEnd() - (float) taskSolution
							.getTaskStart())); 
			 			float x = duration - (int) duration;
			 			int finalDuration = 0;
						if(0 < x){
							finalDuration = (int) Math.ceil(duration);
						}else{
							finalDuration = Math.round(duration);
						}
					
					%>
			 		id:<%=i++%>, text:"<%=taskSolution.getId()%>", start_date:"<fmt:formatDate type="date" value="${today}" pattern="dd-MM-yyyy"/>", duration:<%=finalDuration%>,realDuration:<%=(df.format((float) taskSolution.getTaskEnd() - (float) taskSolution
								.getTaskStart()))%>, order:10,
                 progress:0.0, open: true, participation:"<%=participation%>"
                },
			</c:forEach>
            ],
                    links:[
//             { id:1, source:1, target:2, type:"0"},
        ]
        };

//      gantt.config.readonly = true;
//         gantt.config.work_time = true; //removes non-working time from calculations 
// 		gantt.skip_off_time = true;    //hides non-working time in the chart
// 		gantt.templates.scale_cell_class = function(date){
// 		    if(date.getDay()==0||date.getDay()==6){
// 		        return "weekend";
// 		    }
// 		};
// 		gantt.templates.task_cell_class = function(item,date){
// 		    if(date.getDay()==0||date.getDay()==6){ 
// 		        return "weekend" ;
// 		    }
// 		};
		
		gantt.config.columns = [
		                        {name:"text",       label:"Task name",   tree:true },
// 		                        {name:"start_date", label:"Start time", align: "center" },
		                        	{name:"realDuration",   label:"Duration",   align: "center" }
		                    ];
		gantt.templates.tooltip_text = function(start,end,task){
		    return "<b>Task:</b> "+task.text+"<br/><b>Duration:</b> " + task.realDuration+"<br/><b>Resources:</b> " + task.participation;
		};
		
// 		gantt.config.duration_unit = "hour";//an hour
// 		gantt.config.duration_step = 1; 
		
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
				<c:set var="total" value="${task.taskEnd - task.taskStart}" />
				Total time:
				<fmt:formatNumber type="number" maxFractionDigits="2"
					value="${total}" />
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
		class="btn btn-info " /> <br /> <br /> Debug: ${plan.tasks.task}
</div>
<%@include file="../common/footer.jsp"%>