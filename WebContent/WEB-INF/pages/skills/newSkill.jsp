<%@include file="../common/header.jsp"%>
<%@include file="../common/menu.jsp"%>
<div class="container">
	<ul class="breadcrumb">
		<li><a href="<c:url value="/"/>">Home</a></li>
		<li><a href="<c:url value="/projects/projects"/>">Projects</a></li>
		<li><a href="<c:url value="/skills/skills"/>">Skills</a></li>
		<li class="active">New Skills</li>
	</ul>
	<h1>${projectName} </h1>
	
	<%@include file="skillForm.jsp"%>
	
	<%@include file="../common/footer.jsp"%>