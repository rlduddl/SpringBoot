<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><c:out value="${title}" /></title>
</head>
<body>
	<form method="post" action="/member/joinProc">
		<div>
			<label for="userID">?꾩씠??/label>
			<input type="text" id="userID" name="userID" value="" />
			<button type="button" id="btnIdCheck">?꾩씠??以묐났?뺤씤</button>
		</div>
		<div>
			<label for="email">?대찓??/label>
			<input type="text" id="email" name="email" value="" />
			<button type="button" id="btnEmailCheck">?대찓??以묐났?뺤씤</button>
		</div>
		<div>
			<label for="username">?대쫫</label>
			<input type="text" id="username" name="username" value="" />
		</div>
		<div>
			<label for="password">鍮꾨?踰덊샇</label>
			<input type="password" id="password" name="password" value="" />
		</div>
		<div>
			<label for="password2">鍮꾨?踰덊샇 ?뺤씤</label>
			<input type="password" id="password2" name="password2" value="" />
		</div>
		<button type="button" id="btnJoin">媛??/button>
	</form>
	<script>
		// ?꾩씠??以묐났 泥댄겕 ?щ? 蹂??
		let idCheck = false;
		let idDup = false;
		
		// ?대찓??以묐났 泥댄겕 ?щ? 蹂??
		let emailCheck = false;
		let emailDup = false;
		
		const btnIdCheck = document.querySelector('#btnIdCheck');
		// ?꾩씠??以묐났 泥댄겕
		btnIdCheck.addEventListener('click', function(e){
			idCheck = false;
			idDup = false;
			const userID = document.querySelector('#userID').value?.trim();
			
			fetch('/member/checkUserID/' + userID)
				.then((response) => response.json())
				.then((data) => {
					console.log(data);
					const isExist = data.isExist;
					idCheck = true;
					idDup = isExist;
					if (isExist) {
						alert('?대? ?ъ슜以묒씤 ?꾩씠???낅땲??');
						document.querySelector('#userID').focus();
					} else {
						alert('?ъ슜 媛?ν븳 ?꾩씠?붿엯?덈떎.');
					}
				});
		});
		
		const btnEmailCheck = document.querySelector('#btnEmailCheck');
		
		btnEmailCheck.addEventListener('click', function(e){
			emailCheck = false;
			emailDup = false;
			const email = document.querySelector('#email').value?.trim();
			
			fetch('/member/checkEmail/' + email)
				.then((response) => response.json())
				.then((data) => {
					console.log(data)
					const isExist = data.isExist;
					emailCheck = true;
					emailDup = isExist;
					if (isExist) {
						alert('?대? ?ъ슜以묒씤 ?대찓???낅땲??');
						document.querySelector('#email').focus();
					} else {
						alert('?ъ슜 媛?ν븳 ?대찓?쇱엯?덈떎.');
					}
				});
		});
		
		
		const btnJoin = document.querySelector('#btnJoin');
		btnJoin.addEventListener('click', function(e){
			const username = document.querySelector('#username').value?.trim();
			const password = document.querySelector('#password').value?.trim();
			const password2 = document.querySelector('#password2').value?.trim();
			if (username.length < 2) {
				alert('?대쫫? ?먭????댁긽 ?낅젰?섏꽭??');
				document.querySelector('#username').focus();
				return;
			}
			if (password.length < 4) {
				alert('鍮꾨?踰덊샇??4湲???댁긽 ?낅젰?섏꽭??');
				document.querySelector('#password').focus();
				return;
			}
			if (password !== password2) {
				alert('鍮꾨?踰덊샇媛 ?쇱튂?섏? ?딆뒿?덈떎.');
				document.querySelector('#password').focus();
				return;
			}
			if (
				password.length >= 4 && password2.length >= 4 
				&& password === password2 
				&& idCheck && !idDup 
				&& emailCheck && !emailDup
				) {
				console.log('媛?낆쿂由?);
				// ?꾨찓??member/joinProc2
				// get : url?key=value&key=value
				// json post : 
				// {
					// key : value
					// key : value
				// }
				
				const data = {
					method: 'post',
					headers: {
						'Content-Type': 'application/json' 
					},
					body: JSON.stringify({
						userID: document.querySelector('#userID').value.trim(),
						password: document.querySelector('#password').value.trim(),
						password2: document.querySelector('#password2').value.trim(),
						email: document.querySelector('#email').value.trim(),
						username: document.querySelector('#username').value.trim()
					}),
				}
				
				console.log(data)
				
				fetch('/member/joinProc2', data)
					.then((response) => response.json())
					.then((data) => {
						console.log(data);
						const {result, message} = data;
						alert(message);
						if (result) {
							window.location.href = '/member/login';
						}
					});
			} else {
				alert('?뚯썝媛???묒떇???뺤씤?섏꽭??');
				return;
			}
		});
	</script>
</body>
</html>