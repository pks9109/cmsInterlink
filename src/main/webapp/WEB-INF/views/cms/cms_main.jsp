<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="cms_include.jsp"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>interLink&amp;C</title>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#user_pwd").keyup(function(e){
		if(e.keyCode == 13)  
		login(); 
	});
	$("#login_btn").click(function(){
		login();
	});
	$("#signUp_btn").click(function(){
		signUpFun();
	});
	$("#userIdCheck_btn").click(function(){
		userIdCheck();
	});
		$("#ad_password").on('change keyup paste', function () {
		var password = $("#ad_password").val();
		var password2 = $("#ad_password2").val();
		if(password != password2){
			$("#ps_check").text("*비밀번호가 일치하지 않습니다.");
		}else{
			$("#ps_check").text("");
		}
		});
		$("#ad_password2").on('change keyup paste', function () {
			var password = $("#ad_password").val();
		var password2 = $("#ad_password2").val();
		if(password != password2){
			$("#ps_check").text("*비밀번호가 일치하지 않습니다.");
		}else{
			$("#ps_check").text("");
		}
		});
		$("#ad_id").on('change keyup paste', function () {
			$("#idchek_hidden").val("0");
		});
		if ($("#result").val() == 'fail') {
		alert("아이디와 비밀번호를 확인해주세요.");
	}else if($("#result").val() == 'fail_auth'){
		alert("관리자의 승인이 되지 않았습니다.");
	}else if($("#result").val() == 'fail_autherr'){
		alert("접근권한이 없습니다. 관리자에게 권한을 확인하시기 바랍니다.");
	}else if($("#result").val() == 'fail_adminerr'){
		alert("관리자 계정은 관리자 모드로 접속해주시기 바랍니다.");
	}
		
});
</script>
</head>
<body>

<div class="cmsMainCon">
	<div class="con1">
		<a>INTERLINK <b class="colorBlue">&amp; </b>C</a>
	</div> 
	<hr class="bar"> 
	<form id="login_form">
	<div class="mode_ch">
	<input type="radio" id="mode_emp" name="ad_division" value="user" checked="checked" > <label for="mode_emp">사용자 모드</label> &emsp;&emsp;
	<input type="radio" id="mode_admin" name="ad_division" value="admin"> <label for="mode_admin">관리자 모드</label>
	</div>
	<input type="hidden" id="result" name="result" value="${result}" />
	<div class="con2">
		<div class="con2Input">
			<div class="con2Input1">
				<input type="text" id="user_id" name="ad_id" />
			</div>
			<div class="con2Input2">
				<input type="password" id="user_pwd" name="ad_password" />
			</div>
		</div>
		<div class="con2But">
			<a class="con2But1" id="login_btn" style="cursor:pointer;">로그인</a>			
		</div>
	</div>
	</form>
	<div class="con3">
		<div class="con3Text">
			<a class="con2But2" href="#signUp" >REGISTER &#183; </a><a class="con2But2_2" href="#signUp">가입하기</a>
		</div>
	</div>
</div>







<a href="#x" class="overlay" id="signUp"></a>
<div class="popup">
	<div class="popupCon">
		<div class="popupTitle">
			<a class="popupTitle1">회원가입</a><br />
			<a class="popupTitle2">가입 후 관리자가 승인해야 로그인이 가능합니다.</a>
		</div>
		<div class="popupCon2">
			<form id="sign_form">
			<div class="popupCon2_1">
				<div class="popupCon2_1_id">
					<input type="hidden" value="0" id="idchek_hidden" />
					<input type="text" id="ad_id" name="ad_id"	placeholder="아이디"  />
				</div>
				<div class="popupCon2_1_bt">
					<a id="userIdCheck_btn" style="cursor:pointer;">중복확인</a>
				</div>
			</div>
			
			<div class="popupCon2_2">
				<input type="password" id="ad_password" name="ad_password"	placeholder="비밀번호" />
			</div>
			<div class="popupCon2_2">
				<input type="password" id="ad_password2"	placeholder="비밀번호확인" />
			</div>
			
			<div class="popupCon2_2">
				<input type="text" id="ad_name" name="ad_name"	placeholder="이름" />
			</div>
			<div class="popupCon2_2">
				<input type="text" id="ad_contact" name="ad_contact"	placeholder="전화번호" />
			</div>
			
			<div class="popupCon2_1_E">
				<input type="text" id="ad_email" name="ad_email"	placeholder="이메일" />
			</div>
			
			<div class="popupCon2_1_But">
				<a id="signUp_btn" style="cursor:pointer;">REGISTER</a>
			</div>
			</form>
		</div>
	</div>
	
	<a class="close" href="#close"></a>
</div>

</body>
</html>