	$(document).ready(function() {
			
		var conPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)); //contextpath 구함
		
			$("#delete").click(function() {
				if (confirm("정말 삭제하시겠습니까??") == true){    	//확인
				    location.href= conPath + "/board_delete?board_division="+$("#board_division").val()+"&board_seq="+$("#board_seq").val();
				}else{   										//취소
				    return;
				}
			});
			
			$("#searchBtn").click(function() {
				document.list_form.submit();
			});
			
		});
	
	

	/* 파일추가버튼 */
	function addbt() {
		var fileIndex = $(".addtable").children().length+1-1;
		$("#addtd").append(
				'<tr id="file_up' + fileIndex + '"><td><img id="blah" src="#" alt="your image" /></td><td>' + 
				'<input style="width: 100%;" type="file" name="uploadfile[' + fileIndex + ']" id="file_up' + fileIndex + '"  />' +
				'</td>' +'<td align="right"><button name="file_up' + fileIndex + '" type="button" onclick="delbt(this.name)">-</button>' +
				'</td></tr>');
		
	}
	
	/* 추가된 파일 제거버튼  */
	function delbt(thisname) {
		var file_del = thisname;
	 	if (/(MSIE|Trident)/.test(navigator.userAgent)) { 
			// ie 일때 input[type=file] init.
			$("input[id=" + file_del + "]").replaceWith( $("input[id=" + file_del + "]").clone(true) );
			$("tr[id=" + file_del + "]").hide();
		} else {
			 // other browser 일때 input[type=file] init.
			$("input[id=" + file_del + "]").val(""); 
		    $("tr[id=" + file_del + "]").hide();	 
		}
	}
	
	
	//기존 파일 제거버튼
	function delbt_ori(thisname) {
		var file_del = thisname;
	 	if (/(MSIE|Trident)/.test(navigator.userAgent)) { 
			// ie 일때 input[type=file] init.
			$("input[id=" + file_del + "]").replaceWith( $("input[id=" + file_del + "]").clone(true) );
			$("input[id=" + file_del + "]").remove();
			$("td[id=" + file_del + "]").append(
					'<input type="file"  name="uploadfile[0]"  required="required" id="file_up0" />');
		} else {
			 // other browser 일때 input[type=file] init.
			$("input[id=" + file_del + "]").val(""); 
		    $("input[id=" + file_del + "]").remove();
		    $("td[id=" + file_del + "]").append(
			'<input type="file"  name="uploadfile[0]"  required="required" id="file_up0" />');
		}
	}

	//목록 첨부파일 열기
	//나중에 필요시 사용
	/*function over(fileNamee){
		
		var con = document.getElementById("hidden"+fileNamee);
		
		if(con.style.display=='none'){
	        con.style.display = '';
	    }else{
	        con.style.display = 'none';
	    } 
	}*/

	//파일 다운로드
	function downFile(fileName){
		var conPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)); //contextpath 구함
		var file_name = fileName.split('*');
			location.href= conPath + "/boardFileDown?file_name="+encodeURI(file_name[0])+"&board_division="+file_name[1]+"&file_seq="+file_name[2];
	}
	
	//수정폼에서 기존 업로드된 파일 삭제시 id 값 c --> d로 변환시킴
	function delFile(idx){
		
	 	var flagC = $("input[name=flag]").length;	//플래그 C 전체 개수
	 	var flagCnt = $("#flagCnt").val();		
	 	
		var obj = $('#flist_' + idx);
		var obj2 = $('#addfile_' + idx);
		
		$(obj).find('#flag').val("D");
		$(obj).hide();
		$(obj2).show();
		
		$("#flagCnt").val(flagCnt-1);
		
	}
	
	
	function fileUpload() { //jquerymultifile 파일 선택시 카운터 1증가
	      var cn = $("#flagCnt").val();
	      $("#flagCnt").val(parseInt(cn)+1);
	    }
	
	function remove() { //jquerymultifile x클릭시 카운터 1감소
	      var cn = $("#flagCnt").val();
	      $("#flagCnt").val(parseInt(cn)-1);
	    }


	
	//파일 버튼 교체 스크립트   
		$(document).on("click","#newFile",function(e){
			if($("#flagCnt").val() == '1'){
				e.preventDefault();
				$('input[type=file]').last().click();
			}else if($("#flagCnt").val() == '0'){
				e.preventDefault();
				$('input[type=file]').last().click();
			}else{
				e.preventDefault();             
				alert('업로드할 수 있는 최대 갯수는 2개 입니다.');
			}
		});                         
	
	//customer 상태 선택만해도 변경되게
	//나중에 필요시 사용
	/*$(function() {
		$('#status').change(function() {
			var status = this.value;
			if (status == "1") {
				alert("답변상태가 '확인'으로 변경 되었습니다.");
			}else{
				alert("답변상태가 '미확인'으로 변경 되었습니다.");
			}
			$("#board_form").submit(); 
	});
		
	});*/
	
	//체크박스 전체 선택
	//나중에 필요시 사용
	/*function all_check() { 
		
		var chk = $(this).prop("disabled");
		$('input:checkbox[name="check_box"]').each(function() {

			if (chk) {
				$('input[name="check_box"]').each(function() {
					var opt = $(this).prop("disabled");
					if (!opt) {
						$(this).attr("checked", true);
					}
				});
			} else {
				$("input[name='check_box']").attr("checked", false);
			}
		});
	};*/
	
	//파일선택시 텍스트 대체
	$(function(){
		$('.multi').change(function(){
			var fileValue = $('.multi').val().split("\\");
			var fileName = fileValue[fileValue.length-1];
			$('.upload_text').val(fileName);
		});
	});
	
	//게시판 체크박스 삭제
	//나중에 필요 시 사용 할 것
	/*function detailSubmit() { 
		//체크박스 선택에 따른 삭제 유무
		if(confirm("정말로 삭제 하시겠습니까?") == true) {
			if($('input:checkbox[id="checkOne"]').is(":checked") == true)  {
	           document.board_form.action='board_delete';
					alert("삭제 되었습니다.");
	          } else{
	            alert("삭제하실 목록을 체크하여 주십시오.")
	            return;
	             }
			}else{
				alert("취소 되었습니다.");
				return;
			}	
	document.board_form.submit();
	};*/

