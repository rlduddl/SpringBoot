<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
	
	<form id="infoForm" method="post" action="/member/updateInfo">
		<input type="hidden" id="idx" name="idx" value='<c:out value="${userInfo.getIdx()}" />' readonly />
		<div>
			<label for="userID">?꾩씠??label>
			<input type="text" id="userID" name="userID" value='<c:out value="${userInfo.getUserID()}" />' readonly />
		</div>
		<div>
			<label for="password">鍮꾨?踰덊샇<label>
			<input type="password" id="password" name="password" value='' />
		</div>
		<div>
			<label for="password2">鍮꾨?踰덊샇 ?뺤씤<label>
			<input type="password" id="password2" name="password2" value='' />
		</div>
		<div>
			<label for="username">?대쫫<label>
			<input type="text" id="username" name="username" value='<c:out value="${userInfo.getUsername()}" />' />
		</div>
		<div>
			<label for="email">?대찓??label>
			<input type="text" id="email" name="email" value='<c:out value="${userInfo.getEmail()}" />' />
			<button type="button">以묐났?뺤씤</button>
		</div>
		<button type="button" id="btnConfirm">?뺤씤</button>
	</form>
<!--
1. myInfo??session???덈뒗 媛믪쓣 MemberVO濡?罹먯뒪?낇빐??form???묒떇??留뚮뱺??
2. form???ㅼ뼱媛??꾨뱶 : userID(?쎄린?꾩슜), password (怨듬?), username, email (蹂寃쎌떆 以묐났泥댄겕)
3. ?쇱쓣 ?묒꽦?댁꽌 post濡??꾩넚
4. DB????ν븯湲곗쟾??session???덈뒗 idx媛? userID媛?2媛쒕? ?쎌뼱??form???덈뒗 userID? ?쇱튂?섎뒗吏 ?뺤씤
5. DB??session?먯꽌 ?뺤씤??idx媛? userID媛믪쑝濡?議고쉶?섎뒗 ??議댁옱 ?щ? ?뺤씤
6. ?됱씠 議댁옱?섎㈃ update, ?놁쑝硫??ㅻ쪟 硫붿떆吏 異쒕젰
7. update媛 ?섎㈃ ?섏젙?꾨즺 硫붿떆吏 ???쒕뵫?섏씠吏濡??대룞
8. 留뚯빟 ?ㅻ쪟媛 ?섎㈃ forward濡??뺣낫 ?섏젙?섏씠吏濡??ㅼ떆 ?대룞
-->
<script>
	// ???쒖텧 ???ш퀬瑜?諛⑹??섎뒗 李⑥썝?먯꽌 鍮꾨?踰덊샇瑜??낅젰 諛쏅뒗??
	// 留뚯빟 鍮꾨?踰덊샇??蹂寃쏀빐?쇳븯硫?鍮꾨?踰덊샇 ?꾨뱶 2媛????낅젰 諛쏅뒗??
	// 鍮꾨?踰덊샇瑜?2媛??낅젰 諛쏆? 寃쎌슦 鍮꾧탳 泥섎━ ?꾩슂
	// 鍮꾨룞湲??듭떊?쇰줈 泥섎━?대룄 ?곴? ?놁쑝???ㅼ뒿???꾪빐??post濡??쒖텧 泥섎━?쒕떎.
	document.querySelector('#btnConfirm').addEventListener('click', function(e){
		e.preventDefault();
		
		const userID = document.querySelector('#userID').value?.trim();
		const email = document.querySelector('#email').value?.trim();
		const username = document.querySelector('#username').value?.trim();
		const password = document.querySelector('#password').value?.trim();
		const password2 = document.querySelector('#password2').value?.trim();
		
		const email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
		if(!email_regex.test(email)){ 
			alert('?대찓???뺤떇???뺤씤?섏꽭??');
			return false; 
		}
		if (userID === '') {
			alert('?꾩씠?붽? ?놁뒿?덈떎.');
			window.location.href = '/';
			return false;
		}
		if (username.length < 2) {
			alert('?대쫫? 2湲???댁긽 ?낅젰?섏꽭??');
			return false;
		}
		if (password === '' && password2 !== '') {
			alert('鍮꾨?踰덊샇 蹂寃쎌쓣 ?먰븷 寃쎌슦 鍮꾨?踰덊샇? 鍮꾨?踰덊샇 ?뺤씤??紐⑤몢 ?낅젰?섏꽭??');
			return false;
		}
		if (password !== '' && password2 !== '' && password !== password2) {
			alert('鍮꾨?踰덊샇? 鍮꾨?踰덊샇 ?뺤씤 ????ㅻ쫭?덈떎.');
			return false;
		}
		
		document.querySelector('#infoForm').submit();
	});
</script>
	
	
	
	
</body>
</html>









