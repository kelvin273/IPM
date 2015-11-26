<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<a href="/IPM/projects/newProject"><span class="glyphicon glyphicon-plus"></span>New project</a>&nbsp;&nbsp;
	<a href="/IPM/projects/projects"><span class="glyphicon glyphicon-th"></span>Projects</a>
	<br />
</sec:authorize>







