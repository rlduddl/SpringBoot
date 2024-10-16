<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- ModelAndView?먯꽌 ??ν븳 ?곗씠???몄텧 -->
<title>${title}</title>
</head>
<body>
	<c:choose>
		<c:when test="${memberInfo != null}">
			<a href="/member/logout">濡쒓렇?꾩썐</a>
		</c:when>
		<c:otherwise>
			<a href="/member/login">濡쒓렇??/a>
		</c:otherwise>
	</c:choose>
	
</body>
</html>