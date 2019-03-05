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
</head>
<body>
<%@ include file="../cms/cms_header.jsp"%>
<%@ include file="../cms/cms_left_bar.jsp"%>
<input type="hidden" id="pageVal" value="board01" />
<div class="conBody">
	<div class="boardBody">
			<input type="hidden" id="startPageList" name="startPageList" value="${startPage}">
			<input type="hidden" id="totalPage" name="totalPage" value="${totalPage}">
			<input type="hidden" id="totalCnt" name="totalCnt" value="${totalCnt}">
		<form name="list_form"  method="get">
		<input type="hidden" id="startPage" name="startPage" value="">
		<input type="hidden" id="visiblePages" name="visiblePages" value="">
		<div class="boardSearch">
			<input class="searchBox" type="text" placeholder="Search" id="sch_value" name="sch_value" value="${sch_value}" />
			<a><img class="searchImg" id="searchBtn" alt="search" src="resources/cms/search.png"></a>
			<input type="hidden" value="board_title" name="sch_type" />
		</div>
		</form>
		<div class="boardTable">
			<table class="boardListTable">
				<colgroup>
					<col style="width: 5%" />
					<col style="width: 50%" />
					<%-- <col style="width: 15%" /> --%>
					<col style="width: 30%" />
					<col style="width: 15%" />
				</colgroup>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<!-- <th>작성자</th> -->
					<th>일자</th>
					<th>조회수</th>
				</tr>
				<c:if test="${fn:length(board_list) == 0}">	
					<tr>
					<td colspan="4">
					조회 결과가 없습니다.
					</td>
					</tr>
				</c:if>
				<c:if test="${fn:length(board_list) != 0}">
				<c:forEach var="board_list" items="${board_list}"  varStatus="status">
				<tr>
					<c:if test="${board_list.board_notice == 0}">
					<td>
					<!--역순공식: 전체 레코드 수 - ( (현재 페이지 번호 - 1) * 한 페이지당 보여지는 레코드 수 + 현재 게시물 출력 순서 ) -->
	                <c:set var="startpage" value="${startPage-1}" />
	                <c:set var="statuscount" value="${status.count }" />
	                ${totalCnt+1-(startpage*10+statuscount)}</td>
	                </c:if>
	                <c:if test="${board_list.board_notice != 0}">
	                <td>공지</td>
                	</c:if>
					<td class="title"><a href="<%=request.getContextPath()%>/boardUpdate?board_division=${board_division}&board_seq=${board_list.board_seq}" style="color: white; text-decoration: none;">${board_list.board_title}</a></td>
					<td>${board_list.board_register_date}</td>
					<td>${board_list.board_hit}</td>
				</tr>
				</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="boardWrite">
			<c:if test="${auth eq '1'}">
			<a class="writeBtn1" style="color: black; text-decoration: none;" href="<%=request.getContextPath()%>/boardWrite?board_division=notice">글쓰기</a>
			</c:if>
		</div>
		<div class="boardCount">
			<c:if test="${fn:length(board_list) != 0}">
				<ul id="paging">
				</ul>
			</c:if>
		</div>
	
	</div>
</div>

</body>
</html>