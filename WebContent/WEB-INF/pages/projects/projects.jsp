<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<%@include file="../common/header.jsp"%>
	<h1>Projects JSP</h1>

	<%@include file="../common/menu.jsp"%>

	<c:forEach items="${projects}" var="proj">
		<c:out value="${proj.id}" />
		<i>$<c:out value="${proj.name}" /></i>
		<br>
		<br>
	</c:forEach>

	<%@include file="../common/footer.jsp"%>
</body>
</html>