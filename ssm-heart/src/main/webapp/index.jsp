<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.user!=null }">
	<script type="text/javascript">
        window.top.location.href="rest/page/index";
	</script>
</c:if>
<c:if test="${sessionScope.user==null }">
	<script type="text/javascript">
        window.top.location.href="rest/page/home/login";
	</script>
</c:if>
</body>

</html>