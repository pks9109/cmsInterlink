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
//체크박스 선택제어
$(document).ready(function() {
			
			if ($("#result").val() == 'sucess') {
				alert("정보가 수정 되었습니다.");
			}
	
			$("#product_check_all").click(function() {
				
						var chk = $(this).is(":checked");
						if (chk) {
							$('input[name*="ad_seq"]').prop('checked',true);
						} else {
							$('input[name*="ad_seq"]').prop('checked',false);
						}
					});
			
			$("#product_check_all2").click(function() {
				
				var chk = $(this).is(":checked");
				if (chk) {
					$('input[name*="chk"]').prop('checked',true);
				} else {
					$('input[name*="chk"]').prop('checked',false);
				}
			});	
			 $('input:checkbox[name="chk"]').click(function(){
			   				var index = $('input:checkbox[name="chk"]').index(this);
			        if($('input:checkbox[name="chk"]').is(":checked") == true){
			   				$('#ad_rank'+index).removeAttr('disabled');
			   				$('#datepicker'+index).removeAttr('disabled');
			   				$('#datepicker'+index).attr('readonly', true);
			        }else{
			        		$('#ad_rank'+index).attr('disabled', 'true');
			        		$('#datepicker'+index).attr('disabled', 'true');
			        		$('#datepicker'+index).val('');
			        		$('#datepicker'+index).attr('readonly', false);

			        }
			    });
});
</script>
</head>
<body>
<%@ include file="../cms/cms_header.jsp"%>
<%@ include file="../cms/cms_left_bar.jsp"%>
<input type="hidden" id="pageVal" value="adminList" />
<div class="conBody">
	<div class="boardBody">
		<input type="hidden" id="result" name="result" value="${result}">
		<input type="hidden" id="startPageList" name="startPageList" value="${startPage}">
		<input type="hidden" id="totalPage" name="totalPage" value="${totalPage}">
		<input type="hidden" id="totalCnt" name="totalCnt" value="${totalCnt}">
		<form name="list_form"  method="get">
		<input type="hidden" id="division" name="division" value="${division}">
		<input type="hidden" id="startPage" name="startPage" value="">
		<input type="hidden" id="visiblePages" name="visiblePages" value="">
		<div class="adminTopText">
			<a>회원 가입 대기&nbsp;</a><a class="count" href="#signUpList" id="waitAd">&nbsp;${waitCnt}&nbsp;</a><a>명</a>
			<c:if test="${fn:length(wait_list) == 0}">
			<script>
			$("#waitAd").click(function() {	
			alert('대기중인 회원가입이 없습니다.');
			return false;
			});
			</script>
			</c:if>
		</div>
		<div class="boardTable">
			<table class="boardListTable">
				<colgroup>
					<col style="width: 5%" />
					<col style="width: 5%" />
					<col style="width: 5%" />
					<col style="width: 20%" />
					<col style="width: 25%" />
					<col style="width: 25%" />
					<col style="width: 20%" />
				</colgroup>
				<tr>
					<th><input type="checkbox" id="product_check_all" /></th>
					<th>No</th>
					<th>ID</th>
					<th>이름</th>
					<th>연락처</th>
					<th>이메일</th>
					<th>등록일</th>
				</tr>
				<c:if test="${fn:length(employee_list) == 0}">
					<tr>
						<td colspan="7">
							조회 결과가 없습니다.
						</td>
					</tr>
				</c:if>
				<c:if test="${fn:length(employee_list) != 0}">
				<c:forEach var="list" items="${employee_list}"  varStatus="status">
				<c:if test="${list.ad_id != 'interlinknc'}">
				<tr>
					<td>
					<c:if test="${list.ad_etc != 'super'}">
					<input type="checkbox" class="check" id="checkOne" name="ad_seq" value="${list.ad_seq}" />
					</c:if>
					</td>
					<td>
						<!--역순공식: 전체 레코드 수 - ( (현재 페이지 번호 - 1) * 한 페이지당 보여지는 레코드 수 + 현재 게시물 출력 순서 ) -->
		                <c:set var="startpage" value="${startPage-1}" />
		                <c:set var="statuscount" value="${status.count }" />
		                ${totalCnt+1-(startpage*10+statuscount)} 
					</td>
					<td>${list.ad_id}</td>
					<td><a style="color: white; text-decoration: none;" href="<%=request.getContextPath()%>/adminUpdate?ad_seq=${list.ad_seq}">${list.ad_name}</a></td>
					<td>${list.ad_contact}</td>
					<td>${list.ad_email}</td>
					<!-- 구분자로 일자까지만 자르기 -->
					<td>${fn:substring(list.ad_register_date, 0, 10)}</td>
				</tr>
				</c:if>
				</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="boardWrite">
			<a class="writeBtn" onclick="adminDeleteSubmit()">DELETE</a>
		</div>
		<div class="boardCount">
			<!-- <ul>
				<li>&laquo;</li>
				<li>1</li>
				<li>2</li>
				<li>3</li>
				<li>4</li>
				<li>5</li>
				<li>&raquo;</li>
			</ul> -->
			<c:if test="${fn:length(employee_list) != 0}">
				<ul id="paging">
				</ul>
			</c:if>
		</div>
		</form>
	</div>
</div>

<a href="#x" class="adminPopup" id="signUpList"></a>
<div class="adminPopupDiv">
	<div class="titleDiv">
		<a class="title">가입 대기자</a>
	</div>
	<form name="wait_form"  method="get">
	<div class="adminPopTable">
		<table>
			<colgroup>
				<col style="width: 20%" />
				<col style="width: 20%" />
				<col style="width: 30%" />
				<col style="width: 30%" />
			</colgroup>
			<tr>
				<th>
				<input type="checkbox" id="product_check_all2" />
				</th>
				<th>이름</th>
				<th>id</th>
				<th>신청일</th>
			</tr>
			<c:forEach var="wait_list" items="${wait_list}"  varStatus="status">
			<tr>
				<td><input type="checkbox" id="checkOne" name="chk" value="${wait_list.ad_seq}" /></td>
				<td>${wait_list.ad_name}</td>
				<td>${wait_list.ad_id}</td>
				<!-- 구분자로 일자까지만 자르기 -->
				<td>${fn:substring(wait_list.ad_register_date, 0, 10)}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="boardWrite">
		<select name="ad_division">
  			<option value="1">사용자</option>
  			<option value="9">관리자</option>
		</select>
		<input type="hidden" name="ad_auth" value="1">
		<a class="writeBtn" onclick="waitDelete()">거절</a>
		<a class="writeBtn" onclick="waitSubmit()">승인</a>
	</div>
	</form>
	
<a class="adminPopupClose" href="#close"></a>
</div>
</body>
</html>