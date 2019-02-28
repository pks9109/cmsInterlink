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

$(document).ready(function() {
	
	//textarea --> CKEDITOR 대체
	CKEDITOR.replace('board_content');
	
$(document).on("click","#save",function(e){
	
	if (confirm("글을 등록 하시겠습니까?") == true){    //확인
			// 제목 유효성 검사
			if($("#board_title").val() == '' || $("#board_title").val() == null ){
			    alert("제목을 입력해주세요.");
			    return false;
			}
			if($("#board_title").val().length > 41){
			    alert("제목은 20자이상 입력할 수 없습니다.");
			    return false;
			}
	// 이부분에 에디터 validation 검증
	$("#board_form").submit(); 
	}else{   
	    return;
	}

 });
	
});
</script>
</head>
<body>
<%@ include file="../cms/cms_header.jsp"%>
<%@ include file="../cms/cms_left_bar.jsp"%>
<input type="hidden" id="pageVal" value="board01" />
<div class="conBody">
	<div class="boardBody">
		<div class="boardWriteTitle">
			<a class="title">WRITE</a>
		</div>
		<form id="board_form" name="board_form" method="post" action="boardWriteAction" enctype="multipart/form-data">
		<input type="hidden" id="board_division" name="board_division" value="${board_division}"/>
		<input type="hidden"  name="flagCnt" id="flagCnt" value="0" />
		<div class="boardTable01">
			<ul class="boardUl01">
				<li>구분</li>
				<li>
					<!-- <input type="checkbox" id="check01" /><label for="check01">홈페이지</label>
					<input type="checkbox" id="check02" /><label for="check02">유지보수</label>
					<input type="checkbox" id="check03" /><label for="check03">등등등</label> -->
					<input type="checkbox" id="board_notice" name="board_notice" value="1"><label for="board_notice"> Notice</label>
				</li>
			</ul>
			<ul class="boardUl02">
				<li>
					<label>제목</label>
					<input type="text" name="board_title" id="board_title" />
				</li>
				<!-- <li>
					<label>사업기간</label>
					<input type="text" />
				</li> -->
			</ul>
			<!-- <ul class="boardUl02">
				<li>
					<label>발주처</label>
					<input type="text" />
				</li>
				<li>
					<label>연결주소</label>
					<input type="text" />
				</li>
			</ul> -->
			
			<ul class="boardUl03">
				<li><textarea name="board_content" id="board_content"></textarea></li>
			</ul>
			
			<ul class="boardUl04">
				<li>
					<input type="file" name="uploadfile" id="input_file" class="multi" data-maxfile="3072" title="파일찾기" style="display: none;" onchange="fileUpload(this)" />  
				</li>
				<li	class="fileBut">
					<a id="newFile" >파일 첨부</a>
					<!-- <a>선택 삭제</a> -->
				</li>
				<li class="writeBut">
					<a id="save">SAVE</a>
				</li>
			</ul>
		
		</div>
		</form>
	</div>
</div>
</body>
</html>