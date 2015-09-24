<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<%@include file="../common/header.jsp"%>
	<h1>Skills</h1>

	<%@include file="../common/menu.jsp"%>
	<a href="/IPM/skills/newSkill">New Skill</a>
<br/>
	<c:forEach items="${skills}" var="s">
		<c:out value="${s.id}" />
		<i>$<c:out value="${s.name}" /> <c:out value="${s.projectId}" /></i>
		<br>
		<br>
	</c:forEach>
	<%@include file="../common/footer.jsp"%>
</body>
</html>