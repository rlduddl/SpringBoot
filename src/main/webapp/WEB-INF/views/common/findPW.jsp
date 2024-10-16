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
			<label for="userID">?꾩씠??/label>
			<input type="text" id="userID" name="userID" placeholder="?꾩씠?붾? ?낅젰?섏꽭??" value="" />
		</div>
		<div>
			<label for="email">?대찓??/label>
			<input type="text" id="email" name="email" placeholder="?대찓?쇱쓣 ?낅젰?섏꽭??" value="" />
		</div>
		<button type="submit">?뺤씤</button>
	</form>
	<a href="/member/join">?뚯썝媛??/a>
	<a href="/member/login">濡쒓렇??/a>
	<a href="/member/findID">?꾩씠?붿갼湲?/a>
	<script>
		document.querySelector('#userForm').addEventListener('submit', function(e){
			e.preventDefault();
			const userID = document.querySelector('#userID').value?.trim();
			const email = document.querySelector('#email').value?.trim();
			
			const data = {
				method: 'post',
				headers: {
					'Content-Type': 'application/json' 
				},
				body: JSON.stringify({
					userID: userID,
					email: email,
				}),
			}
							
			fetch('/member/changePW', data)
				.then((response) => response.json())
				.then((data) => {
					console.log(data);
					const {result, message} = data;
					alert(message);
				});
			// ?꾩씠?? ?대찓?쇱쓣 ?낅젰 諛쏆븘??鍮꾨?踰덊샇瑜?蹂寃쏀븯怨?
			// 蹂寃쏀븳 鍮꾨?踰덊샇瑜?alert李쎌뿉 ?꾩썙以??
			// end point??/member/changePW 瑜??ъ슜?섍퀬 
			// 1. ?꾩씠?? ?대찓?쇰줈 議고쉶?섎뒗 ?됱쓽 pk媛믪씠 ?덈뒗吏 泥댄겕
			// 2. ?놁쑝硫?alert李쎌뿉 硫붿떆吏瑜?諛쏆쓣 ?섏엳寃?援ъ꽦 ??由ы꽩
			// 3. ?덉쑝硫??먮컮?먯꽌 ?쒕뜡 臾몄옄?댁쓣 留뚮뱾怨?DB??湲곕줉?쒕떎.
			//    湲곕줉?좊븣 ?앹꽦??臾몄옄?댁쓣 由ы꽩?댁???
		});
	</script>
</body>
</html>









