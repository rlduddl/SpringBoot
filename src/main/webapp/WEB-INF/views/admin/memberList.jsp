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
<style>
	table {
		width: 100%;
		border-collapse: collapse;
	}
</style>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>踰덊샇</th>
				<th>沅뚰븳</th>
				<th>?꾩씠??/th>
				<th>鍮꾨쾲</th>
				<th>?대쫫</th>
				<th>?대찓??/th>
				<th>媛?낆씪</th>
				<th>怨꾩젙?ъ슜?щ?</th>
				<th>?덊눜??/th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td><c:out value="${vo.getIdx()}" /></td>
					<td>
						<c:choose>
							<c:when test="${vo.getIsAdmin() == 1}">
								愿由ъ옄
							</c:when>
							<c:otherwise>
								?쇰컲?ъ슜??
							</c:otherwise>
						</c:choose>
					</td>
					<td><c:out value="${vo.getUserID()}" /></td>
					<td><c:out value="${vo.getPassword()}" /></td>
					<td><c:out value="${vo.getUsername()}" /></td>
					<td><c:out value="${vo.getEmail()}" /></td>
					<td><c:out value="${vo.getRegDate()}" /></td>
					<td><c:out value="${vo.getIsUse()}" /></td>
					<td><c:out value="${vo.getDropDate()}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>