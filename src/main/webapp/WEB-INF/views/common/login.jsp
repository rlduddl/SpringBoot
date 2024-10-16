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
	<form id="loginForm" method="post" action="/member/loginProc">
		<div>
			<label for="userID">?꾩씠??/label>
			<input type="text" id="userID" name="userID" placeholder="?꾩씠?붾? ?낅젰?섏꽭??" value="<c:out value='${userID}' />" />
		</div>
		<div>
			<label for="password">鍮꾨?踰덊샇</label>
			<input type="text" id="password" name="password" placeholder="鍮꾨?踰덊샇瑜??낅젰?섏꽭??" value="<c:out value='${password}' />" />
		</div>
		<button type="submit">濡쒓렇??/button>
	</form>
	<a href="/member/join">?뚯썝媛??/a>
	<a href="/member/findID">?꾩씠?붿갼湲?/a>
	<a href="/member/findPW">鍮꾨?踰덊샇李얘린</a>
	<script>
		// 1. ?꾩씠???낅젰?щ? 泥댄겕 : 3???댁긽 ?낅젰
		// 2. 鍮꾨쾲 ?낅젰?щ? 泥댄겕 : 3???댁긽 ?낅젰
		// 3. 1,2??ぉ 留뚯” ??form submit
		document.querySelector('#loginForm').addEventListener('submit', function(e) {
			e.preventDefault();
			const userID = document.querySelector('#userID').value?.trim();
			const password = document.querySelector('#password').value?.trim();
			if (userID.length < 3) {
				alert('?꾩씠?붾뒗 3???댁긽 ?낅젰?섏꽭??');
				return;
			}
			if (password.length < 3) {
				alert('鍮꾨?踰덊샇??3???댁긽 ?낅젰?섏꽭??');
				return;
			}
			this.submit();
		});
		
		const id = "<c:out value='${userID}' />";
		const pw = "<c:out value='${password}' />";
		if (id && pw) alert('怨꾩젙???뺤씤?섏꽭??');
	</script>
</body>
</html>









