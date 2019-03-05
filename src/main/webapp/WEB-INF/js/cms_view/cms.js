jQuery(document).ready(function($){
	/*왼쪽 바 액션*/
	var page = $("#pageVal").val();
	$("#"+page).attr("class","check");
	/*왼쪽바 반응형*/
	$(window).resize(function (){
	  var width_size = window.outerWidth;
	  if (width_size <= 790) {
		  var i = $("#toggleBtn").is(":checked");
		  if(i==false){
		  	$(".cmsLeft").css("visibility", "hidden");
		  }else{
			$(".cmsLeft").css("visibility", "hidden");
			$("#toggleBtn").prop('checked', false);
		  }
	  }
	  if (width_size > 790) {
		  var i = $("#toggleBtn").is(":checked");
		  if(i==false){
				$(".cmsLeft").css("visibility", "visible");
				$("#toggleBtn").prop('checked', true);
		  }else{
				$(".cmsLeft").css("visibility", "visible");
		  }
	  }
	});
	$(".dropBut").click(function(){
		var i = $("#toggleBtn").is(":checked");
		if(i==false){
			$(".cmsLeft").css("visibility", "hidden");
		}else{
			$(".cmsLeft").css("visibility", "visible");
		}
	});
	/*왼쪽바 end*/
	
});
		
		//로그인
		function login() {
			
			var conPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)); //contextpath 구함 
			if($("#user_id").val().length <1){
				alert("아이디를 입력하세요.");
				this.focus();
			}else if($("#user_pwd").val().length <1){
				alert("비밀번호를 입력해주세요");
				this.focus();
			}else{
			var url = conPath+"/loginTry";
			var comSubmit = new ComSubmit("login_form");
			comSubmit.setUrl(url);
			comSubmit.submit();
			}
		}
		
		//아이디 중복체크
		function userIdCheck() {
			var userid = "userid="+$("#ad_id").val();
			$.ajax({
		        type : 'POST',
		        data : userid,
		        url : "userIdCheck",
		        dataType: "json",
		        success : function(data) {
		            if (data.cnt > 0) {
		                alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
		            } else {
		            	$("#idchek_hidden").val("1");
		                alert("사용가능한 아이디입니다.");
		            }
		        },
		        error : function(error) {
		            alert("error : " + error);
		        }
		    });
		}
		
		//회원가입 유효성
		function signUpFun() {
			
			var conPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)); //contextpath 구함
			var regNumber = /^\d{2,3}-\d{3,4}-\d{4}$/;
	    	var temp = $("#ad_contact").val();
	    	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; //이메일형식체크
	    	
			if($("#ad_id").val().length <5){
				alert("아이디는 5자이상 입력하세요.");
				this.focus();
			}else if($("#ad_password").val().length <1){
				alert("비밀번호를 입력해주세요");
				this.focus();
			}else if($("#ad_password2").val().length <1){
				alert("비밀번호 확인을 입력해주세요");
				this.focus();
			}else if($("#ad_password").val() != $("#ad_password2").val()){
				alert("비밀번호가 서로 틀립니다 다시 입력해주세요.");
				$("#ad_password").val("");
				$("#ad_password2").val("");
				this.focus();
			}else if($("#ad_name").val().length <1){
				alert("이름을 입력해주세요");
				this.focus();
			}else if($("#ad_contact").val().length <1){
				alert("연락처를 입력해주세요.");
				this.focus();
			}else if(!regNumber.test(temp)){	
		        $("#ad_contact").val("");
		        alert("연락처를 알맞을 형식으로 입력해주세요.(-포함)" +'\n'+ "ex)010-0000-0000");
		        this.focus();
			 }else if($("#ad_email").val().length <1){
				alert("이메일을 입력해주세요.");
				this.focus();
			 }else if(exptext.test($("#ad_email").val()) == false) {
				$("#ad_email").val('');
				alert("이메일형식이 바르지 않습니다."+'\n'+"ex)info@interlinknc.com");
				this.focus();
			}else if($("#idchek_hidden").val()!="1"){
				alert("아이디 중복채크를 해주세요.");
				this.focus();
			}else{
				var url = conPath + "/sign_form_insert";
				var comSubmit = new ComSubmit("sign_form");
				comSubmit.setUrl(url);
				comSubmit.submit();
				alert("가입되었습니다. 관리자에게 승인 요청을 해주세요.");
			}
		}
		
		//로그아웃
		function logout() {
			var conPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2)); //contextpath 구함
			
			location.href = conPath + "/logout";
		}
				
		//내정보수정 유효성
		function admin_modify() { 
			var str_space = /\s/; //띄어쓰기체크
			var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; //이메일형식체크
			
			//연락처 유효성 검사
			if($("#ad_contact").val() == '' || $("#ad_contact").val() == null || str_space.exec($("#ad_contact").val()) ){
				$("#ad_contact").focus();
				$("#ad_contact").val('');
				alert("연락처를 입력해주세요.");
				return false;
			}
			var regNumber = /^\d{2,3}-\d{3,4}-\d{4}$/;
		    var temp = $("#ad_contact").val();
		    if(!regNumber.test(temp))
		    {
		    	$("#ad_contact").focus();
		        $("#ad_contact").val("");
		        alert("연락처를 알맞을 형식으로 입력해주세요.(-포함)" +'\n'+ "ex)010-0000-0000");
				return false;
				
		    }
			
			//이메일 유효성 검사
			if($("#ad_email").val() == '' || $("#ad_email").val() == null || str_space.exec($("#ad_email").val()) ){
				$("#ad_email").focus();
				$("#ad_email").val('');
				alert("이메일을 입력해주세요.");
				return false;
			}
			if(exptext.test($("#ad_email").val()) == false) {
				$("#ad_email").focus();
				$("#ad_email").val('');
				alert("이메일형식이 바르지 않습니다."+'\n'+"ex)info@interlinknc.com");
				return false;
			}
			$("#admin_form").submit(); 
		}
			
			//사원 체크삭제
			function adminDeleteSubmit() { 
		    	//체크박스 선택에 따른 삭제 유무
				if(confirm("정말로 삭제 하시겠습니까?") == true) {
					if($('input:checkbox[id="checkOne"]').is(":checked") == true)  {
			           document.list_form.action='admin_delete';
							alert("삭제 되었습니다.");
			          } else{
			            alert("삭제하실 목록을 체크하여 주십시오.")
			            return;
			             }
					}else{
						alert("취소 되었습니다.");
						return;
					}	
		    document.list_form.submit();
		  };
		  
		  //회원가입 대기자 삭제
		  function waitDelete() { 
		  	//체크박스 선택에 따른 삭제 유무
		  	$("#ad_rank").remove();
				if(confirm("회원가입을 거절 하시겠습니까?") == true) {
					if($('input:checkbox[id="checkOne"]').is(":checked") == true)  {
			           document.wait_form.action='wait_delete';
							alert("리스트에서 제거 되었습니다.");
			          } else{
			            alert("삭제하실 목록을 체크하여 주십시오.")
			            return;
			             }
					}else{
						alert("취소 되었습니다.");
						return;
					}	
		  document.wait_form.submit();
		};
		
		//회원가입 승인
		function waitSubmit() { 
				if(confirm("해당 대기자들을 승인하시겠습니까?") == true) {
					if($('input:checkbox[id="checkOne"]').is(":checked")){
						var index = $('input:checkbox[name="chk"]').index(this);
						document.wait_form.action='wait_admit';
						alert("가입이 승인 되었습니다.");
			          } else{
			            alert("승인하실 목록을 체크하여 주십시오.")
			            return;
			             }
					}else{
						alert("취소 되었습니다.");
						return;
					}	
		  document.wait_form.submit();
		};	
		
		//datepiker 포멧
		//현재 미사용, 추후에 사용 할 예정
		/*$(function() { 
		
			  $('input:text[name="ad_hiredate"]').datepicker({
			    dateFormat: 'yy년 mm월 dd일'
			  });
		});*/
		
		//패스워드변경 submit
		function password_submit() {
			$("#password_modify").submit(); 
		}
		
		//관리자 페이지 리스트 페이징
		$(document).ready(function() {
		var totalData = $("#totalCnt").val();    // 총 데이터 수
	    var dataPerPage = 10;    // 한 페이지에 나타낼 데이터 수
	    var pageCount = "";        // 한 화면에 나타낼 페이지 수
	    if($("#totalPage").val() == "1"){
	    pageCount = 1;
	    }else if($("#totalPage").val() == "2"){
	    pageCount = 2;	
	    }else if($("#totalPage").val() == "3"){
		pageCount = 3;	
		}else if($("#totalPage").val() == "4"){
		pageCount = 4;	
		}else{
		pageCount = 5;	
		}
	    var sp = $('#servletPath').val();
	    var next = "";
	    var prev = "";
	    function paging(totalData, dataPerPage, pageCount, currentPage){
	    	var startPage = $('#startPageList').val(); //현재 페이지
	        console.log("currentPage : " + startPage);
	        
	        var totalPage = Math.ceil(totalData/dataPerPage);    // 총 페이지 수
	        var pageGroup = Math.ceil(startPage/pageCount);    // 페이지 그룹
	        console.log("pageGroup : " + pageGroup);
	        
	        var last = pageGroup * pageCount;    // 화면에 보여질 마지막 페이지 번호
	        if(last > totalPage){
	        	last = totalPage;
	        }
	        var first = last - (pageCount-1);    // 화면에 보여질 첫번째 페이지 번호
	        
	        var next = last+1;
	        var prev = first-1;
	        
	        console.log("last : " + last);
	        console.log("first : " + first);
	        console.log("next : " + next);
	        console.log("prev : " + prev);
	 
	        var pingingView = $("#paging");
	        
	        var html = "";
	        	if(prev >= 5){
	        	html +=
	        		'<li class="downCntli1">' +
	        		'<div name="page_move" id="start" start_page="'+1+'" style="margin-right: 3px;" >' +
	    			'<img alt="처음으로" src="resources/cms/ll_bt.png">'+
	    			'</div>' +
	    			'<div name="page_move" id="prev" start_page="'+prev+'">' +
	    			'<img alt="앞으로" src="resources/cms/l_bt.png">' +
		            '</div>'+
	    			'</li>';
	        	}else if(prev < 5){
	        	html +=	
	        		'<li class="downCntli1">' +
	        		'<div id="start" start_page="'+1+'" style="margin-right: 3px;" >' +
	    			'<img alt="처음으로" src="resources/cms/ll_bt.png">'+
	    			'</div>' +
	    			'<div id="prev" start_page="'+prev+'" >' +
	    			'<img alt="앞으로" src="resources/cms/l_bt.png">' +
		            '</div>'+
	    			'</li>';
	        	}
	        	
	        	
	        	for(var i=first; i <= last; i++){
	        		if (startPage == i) {
	        			html += '<li><a id="'+i+'" name="page_move" start_page="'+i+'" style="cursor:pointer;" disabled>'+ i +'</a></li>';
	        		}else {
	        			html += '<li><a id="'+i+'" name="page_move" start_page="'+i+'" style="cursor:pointer;">'+ i +'</a></li>';	
	        		}
	        	}
	    	if(last < totalPage){
		        	html +=
	        		'<li class="downCntli2">' +
	        		'<div name="page_move" id="totalPage" start_page="'+next+'" style="margin-right: 3px;" >' +
	    			'<img alt="다음" src="resources/cms/r_bt.png">'+
	    			'</div>' +
	    			'<div name="page_move" id="totalPage" start_page="'+totalPage+'">' +
	    			'<img alt="맨뒤로" src="resources/cms/rr_bt.png">' +
		            '</div>'+
	    			'</li>';
	    	}else if(last == totalPage){
	    		
		        	html +=
	        		'<li class="downCntli2">' +
	        		'<div id="totalPage" start_page="'+next+'" style="margin-right: 3px;" >' +
	    			'<img alt="다음" src="resources/cms/r_bt.png">'+
	    			'</div>' +
	    			'<div id="totalPage" start_page="'+totalPage+'">' +
	    			'<img alt="맨뒤로" src="resources/cms/rr_bt.png">' +
		            '</div>'+
	    			'</li>';
	    	}
	    	
	        $("#paging").append(html);    // 페이지 목록 생성
            $("#paging a." + startPage).addClass("focus");    // 현재 페이지 표시
            $("#paging a#" + startPage).addClass("focus");    // 현재 페이지 표시
	        
            /*$("#paging a").click(function(){
	            
	            var item = $(this);
	            
	            var id = item.attr("id");
	            var selectedPage = item.text();
	            
	            if(id == "next")    selectedPage = next;
	            if(id == "prev")    selectedPage = prev;
	            if(id == "start")    selectedPage = 1;
	            if(id == "end")    selectedPage = totalPage;
	            
	            paging(totalData, dataPerPage, pageCount, selectedPage);
	        });*/
	                                           
	    }
	    
	    $(document).ready(function(){        
	        paging(totalData, dataPerPage, pageCount, 1);		        
	    });
	    
	    //페이지 하단바 번호 클릭 시 이동
	    $(document).off("click", "[name='page_move']").on("click","[name='page_move']",function() {
			var id_check = $(this).attr("id"); //해당 seq값을 가져오기위해 새로 추가
			var totalPage = $('#totalPage').val(); //다운로드 목록 전체 페이지 수
			var visiblePages = 10;//리스트 보여줄 페이지
			var sp = $('#servletPath').val();
			$('#startPage').val($(this).attr("start_page"));//보고 싶은 페이지
			var startPageList = $('#startPage').val();
			$('#startPageList').val(startPageList);
			var startPage = $('#startPageList').val(); 
			$('#visiblePages').val(visiblePages);
			
			document.list_form.submit();

		});
	});