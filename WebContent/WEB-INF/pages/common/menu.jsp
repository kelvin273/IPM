<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<a href="/IPM/projects/newProject">New project</a>
	<a href="/IPM/projects/projects">Projects</a>
	<br />
</sec:authorize>







