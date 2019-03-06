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
	
	//CK에디터 함수 추가
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
 	
	var flagC = $("input[name=flag]").length;	//플래그 C 전체 개수
	var flagCnt = $("#flagCnt");		
	
	flagCnt.val(flagC);
	
});

</script>
<script type="text/javascript">
//wait for document to load

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
		<form id="board_form" name="board_form" method="post" action="boardUpdateAction" enctype="multipart/form-data">
		<input type="hidden" id="board_division" name="board_division" value="${board_division}"/>
		<input type="hidden" id="board_seq" name="board_seq" value="${board_update.board_seq}">
		<div class="boardTable01">
			<ul class="boardUl01">
				<li>구분</li>
				<li>
					<c:if test="${board_update.board_notice == 1}">
						  <input type="checkbox" id="board_notice" name="board_notice" value="1" checked="checked"><label for="board_notice"> Notice</label>
                    </c:if>
                    <c:if test="${board_update.board_notice == 0}">
	                      <input type="checkbox" id="board_notice" name="board_notice" value="1"><label for="board_notice"> Notice</label>
                    </c:if>
				</li>
			</ul>
			<ul class="boardUl02">
				<li>
					<label>제목</label>
					<input type="text" name="board_title" id="board_title" value="${board_update.board_title}" />
				</li>
			</ul>
			<ul class="boardUl03">
				<li><textarea name="board_content" id="board_content">${board_update.board_content}</textarea></li>
			</ul>
			
			<ul class="boardUl04">
				<li style="overflow-x: hidden; overflow-y: scroll; ">
					<input type="hidden"  name="flagCnt" id="flagCnt" value="" />
					<c:forEach var="file_list" items="${file_list}"  varStatus="status">
					<c:if test="${file_list.file_use_yn eq 'Y'}">
					<div id="flist_${status.count}">
						 	<c:set var="TextValue" value="${file_list.file_sub_name}"/>
							<input type="hidden" name="file_key" id="file_key" value="${file_list.file_seq}" />
							<input type="hidden" id="update_id" name="update_id" value="${sessionScope.admin_id}" />
							<input type="hidden"  name="flag" id="flag" value="C" />
							 &ensp;파일 이름 : ${file_list.file_ori_name} | 파일 크기 : ${file_list.file_size} KB
							<%-- <button type="button" id="downBtn" onclick="delFile('${status.count}');">삭제</button> --%>
							<a style="color: red;" onclick="delFile('${status.count}');" >X</a>
							<a id="downBtn" style="color: red;" onclick="downFile('${file_list.file_sub_name}');" >DOWN</a>
							<br/>
					</div>		
					</c:if>
					</c:forEach>
					<div id="addFile"> 
					<input type="file" name="uploadfile" id="input_file" class="multi" data-maxfile="3072" title="파일찾기" style="display: none;" onchange="fileUpload(this)" />  
					</div>
				</li>
				<li	class="fileBut">	
					<c:if test="${auth eq '1'}">
					<a id="newFile" >파일 첨부</a>
					</c:if>
					<!-- <a>선택 삭제</a> -->
				</li>
				<li class="writeBut">
					<c:if test="${auth eq '1'}">
					<a id="save">SAVE</a>
					<a id="delete">DELETE</a>
					</c:if>
				</li>
			</ul>
		
		</div>
		</form>
	</div>
</div>
</body>
</html>