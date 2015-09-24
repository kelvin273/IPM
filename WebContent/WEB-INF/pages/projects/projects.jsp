<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<%@include file="../common/header.jsp"%>
	<h1>Projects JSP</h1>

	<%@include file="../common/menu.jsp"%>

<a href="/IPM/projects/newProject">New project</a>
<a href="/IPM/projects/projects">Projects</a>

<br/>
	<c:forEach items="${projects}" var="proj">
		<c:out value="${proj.id}" />
		<i><a href="/IPM/skills/skills?projectId=<c:out value="${proj.id}" />"><c:out value="${proj.name}" /></a></i>
		<br/>
		<br/>
	</c:forEach>

	<%@include file="../common/footer.jsp"%>
</body>
</html>