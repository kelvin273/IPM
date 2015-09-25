<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<%@include file="../common/header.jsp"%>
	<h1>Projects JSP</h1>

	<%@include file="../common/menu.jsp"%>

	<a href="/IPM/projects/newProject">New project</a>
	<a href="/IPM/projects/projects">Projects</a>

	<br />
	<c:forEach items="${projects}" var="proj">

		<br />
		<c:out value="${proj.id}"/> <c:out value="${proj.name}"/>
		<form:form method="POST" action="/IPM/skills/skills">
			<input id="projectId" name="projectId" type="hidden"
				value="<c:out value="${proj.id}"/>" />
			<input type="submit" value="skills" />
		</form:form>
		
		<form:form method="POST" action="/IPM/resources/resources">
			<input id="projectId" name="projectId" type="hidden"
				value="<c:out value="${proj.id}"/>" />
			<input type="submit" value="resources" />
		</form:form>
		
	</c:forEach>

	<%@include file="../common/footer.jsp"%>
</body>
</html>