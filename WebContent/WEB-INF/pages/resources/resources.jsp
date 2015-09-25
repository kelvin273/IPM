<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<%@include file="../common/header.jsp"%>
	<h1>Resources</h1>

	<%@include file="../common/menu.jsp"%>
	<a href="/IPM/resources/newResource">New Resource</a>
<br/>
	<c:forEach items="${resources}" var="r">
		<c:out value="${r.id}" />
		<i>$<c:out value="${r.name}" /> <c:out value="${r.projectId}" /></i>
		<br>
		<br>
	</c:forEach>
	<%@include file="../common/footer.jsp"%>
</body>
</html>