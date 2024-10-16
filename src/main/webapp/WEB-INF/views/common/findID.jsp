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
	<form id="userForm" method="get" action="">
		<div>
			<label for="email">?대찓??/label>
			<input type="text" id="email" name="email" placeholder="?대찓?쇱쓣 ?낅젰?섏꽭??" value="" />
		</div>
		<button type="submit">?뺤씤</button>
	</form>
	<a href="/member/join">?뚯썝媛??/a>
	<a href="/member/login">濡쒓렇??/a>
	<a href="/member/findPW">鍮꾨?踰덊샇李얘린</a>
	<script>
		document.querySelector('#userForm').addEventListener('submit', function(e){
			e.preventDefault();
			const email = document.querySelector('#email').value?.trim();
			fetch('/member/findID/' + email)
				.then((res) => res.json())
				.then((data) => {
					console.log(data);
					const {result, message} = data;
					alert(message);
				});
		});
	</script>
</body>
</html>









