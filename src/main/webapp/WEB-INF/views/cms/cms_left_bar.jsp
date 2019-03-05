<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="dropBut">
	<input type="checkbox"	id="toggleBtn" />
	<label for="toggleBtn">&nbsp;</label>
	
</div>
<div class="cmsLeft">
	<ul class="leftbar_ul">
		<li class="jalogo"><img alt="로고" src="resources/cms/gpcamp_logo.png"></li>
		<li class="title">게시판관리</li>
			<li class="" id="board01"><a class="li_A" href="<%=request.getContextPath()%>/boardList?board_division=notice"> 공지사항</a></li>
<!-- 		<li class="" id="board02"><a class="li_A" href="#"> Board02</a></li>
			<li class="" id="board03"><a class="li_A" href="#"> Board03</a></li> -->
		<li class="title">설정</li>
		<c:if test="${sessionScope.ad_division == '9'}"><!-- 계정 권한이 관리자 일때 활성화 -->
			<li class="" id="adminList"><a class="li_A" href="<%=request.getContextPath()%>/adminList"> 사원관리</a></li>
		</c:if>	
			<li class="" id="admin"><a class="li_A" href="<%=request.getContextPath()%>/adminUpdate?ad_seq=${ad_seq}"> 내정보수정</a></li>
	</ul>
</div>