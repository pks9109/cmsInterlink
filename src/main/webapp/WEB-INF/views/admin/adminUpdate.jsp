<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../cms/cms_include.jsp"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>interLink&amp;C</title>
<script type="text/javascript">
jQuery(document).ready(function($){
	$('.passwordBut').click(function() {
    	var p = $('.password');
    	if(p.is(":visible")){
    		$('.adminTable .password input').val("");
    		p.hide();
    		$('#pwCheck').val("0");
    	}else{
    		p.show();
    		$('#pwCheck').val("1");
    	}
    });
});
</script>
</head>
<body>
<%@ include file="../cms/cms_header.jsp"%>
<%@ include file="../cms/cms_left_bar.jsp"%>
<input type="hidden" id="pageVal" value="admin" />
<div class="conBody">
	<div class="adminTitle">
		<a class="title">나의 정보 조회 / 수정</a>
	</div>
	<div class="boardBody">
		<form id="admin_form" name="admin_form" method="post" action="admin_modify_action">
		<div class="adminTable">
			<table>
				<colgroup>
					<col style="width: 20%" />
					<col style="width: 80%" />
				</colgroup>
				<tr><td>이름</td><td>
				<input type="text" id="ad_name" name="ad_name" value="${admin_info.ad_name}" readonly="readonly"/>
				<input type="hidden" id="ad_seq" name="ad_seq" value="${admin_info.ad_seq}" />
				<input type="hidden" id="ss_seq" name="ss_seq" value="${sessionScope.ad_seq}" />			
				<input type="hidden" id="result" name="result" value="${result}" />
				<input type="hidden" id="division" name="division" value="${division}" />
				</td></tr>
				<tr><td>아이디</td><td><input type="text" id="ad_id" name="ad_id" value="${admin_info.ad_id}" readonly="readonly" />
				</td></tr>
				<tr><td>비밀번호</td><td class="textLeft">
					<a class="passwordBut">비밀번호 변경</a>
					<div class="password" style="display: none;">
						<input type="hidden" id="pwCheck" name="pwCheck" value="0" />
						<a>기존비밀번호</a><input type="password" id="ad_ori_password" name="ad_ori_password" />
						<a>새 비밀번호</a><input type="password" id="ad_password" name="ad_password" />
						<a>비밀번호 확인</a><input type="password" id="ad_passwordChk" name="ad_passwordChk" />
					</div>
				</td></tr>
				<tr><td>연락처</td><td><input type="text" id="ad_contact" name="ad_contact" value="${admin_info.ad_contact}" /></td></tr>
				<tr><td>이메일</td><td><input type="text" id="ad_email" name="ad_email" value="${admin_info.ad_email}" /></td></tr>
			</table>
			<div class="pubText">
			<c:if test="${result == 'sucess'}">
				<a style="color: white;" >내 정보가 성공적으로 변경되었습니다!</a>
			</c:if>
			<c:if test="${result == 'fail'}">
				<a style="color: white;" >기존 패스워드가 틀립니다.</a>
			</c:if>
			<c:if test="${result == 'fail_id'}">
				<a style="color: white;" >변경하실 패스워드를 확인해주세요.</a>
			</c:if>
			</div>
			<div class="boardWriteButDiv">
				<a onclick="admin_modify()" style="cursor:pointer;">EDIT</a>
			</div>
		</div>
		</form>
	</div>
</div>
</body>
</html>