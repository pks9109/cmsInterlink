<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="cmsTop">
	<div class="cmsTopLogo">
		<img alt="인터링크앤씨 로고" src="resources/cms/interlinknc_logo.png">
	</div>
	<div class="cmsTopText">
		<a class="cmsTopText1" style="cursor: default;">${sessionScope.ad_name}</a><a class="cmsTopText2" style="cursor: default;"> 님 환영합니다.</a>		
		<a class="cmsTopText3" style="cursor: pointer;">메인</a>
		<a class="cmsTopText4" onclick="logout()">로그아웃</a>
	<script>
	$('.cmsTopText3').click(function(){
		var conPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)); //contextpath 구함
		var con = confirm("메인 홈페이지로 이동시 관리자페이지는 로그아웃됩니다."+'\n'+'\n\n'+"이동하시겠습니까?");
		if(con == true){
		alert("정상적으로 로그아웃 되었습니다.");
		location.href = conPath + "/mainMove";
		}else{
		 return false;	
		}
	});
	</script>
	</div>
</div>