package com.homepage.cmsInterlink.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.homepage.cmsInterlink.model.Admin;
import com.homepage.cmsInterlink.model.Authority;
import com.homepage.cmsInterlink.model.Board;
import com.homepage.cmsInterlink.model.BoardFile;
import com.homepage.cmsInterlink.model.Fileup;
import com.homepage.cmsInterlink.service.AdminService;
import com.homepage.cmsInterlink.service.BoardFileService;
import com.homepage.cmsInterlink.service.BoardService;
import com.homepage.cmsInterlink.util.CommandMap;

@Controller
public class CmsController {
	@Autowired
	private AdminService as;
	@Autowired
    BoardService boardService;
	@Autowired
    BoardFileService boardFileService;
	
	//로그인 및 회원가입
	@RequestMapping(value = "/cms_main") 
	public ModelAndView view_cms_main(HttpServletRequest request, SessionStatus status, HttpServletResponse response,
			CommandMap commandMap, Model model, @RequestParam Map<String, Object> paramMap) throws Exception {
		
		ModelAndView mav = new ModelAndView("cms/cms_main");
		model.addAttribute("result", paramMap.get("result"));
		
		if (request.getSession().getAttribute("ad_id") != null) {
			/*세션 종료 삭제*/
			request.getSession(false).invalidate();
		}
		
		return mav;
	}
	//아이디 중복 검사
	@RequestMapping(value = "/userIdCheck", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String, Object> userIdCheck(@RequestParam("userid") String id){
		int person = as.idcheck(id);
		Map<String,Object>  map = new HashMap<String,Object>();
		map.put("cnt", person);
		return map;
	}
	//로그인 액션
	@RequestMapping(value = "/loginTry")
	public ModelAndView loginTry(HttpServletRequest request, @RequestParam Map<String, Object> map, String ad_division) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		Admin admin = as.selectUserInfo(map);
		
		if(ad_division.equals("admin")) {
			/*관리자로그인*/
			if (admin == null) { 															//admin 정보가 없을때
				mav.setViewName("cms/cms_main");
				mav.addObject("result", "fail");
			}else if(admin.getAd_auth().equals("0")){ 										//회원가입 승인이 안되었을때
				mav.setViewName("cms/cms_main");
				mav.addObject("result", "fail_auth");
			}else if(!admin.getAd_division().equals("9")){									//계정 구분이 관리자가 아닐때 |  9:관리자, 나머지는 사용자
				mav.setViewName("cms/cms_main");
				mav.addObject("result", "fail_autherr");
			}else{																			//위 조건 충족 시 관리자 정보를 세션에 담는다.
				request.getSession().setAttribute("ad_id", map.get("ad_id"));
				request.getSession().setAttribute("ad_seq", admin.getAd_seq());
				request.getSession().setAttribute("ad_name", admin.getAd_name());
				request.getSession().setAttribute("ad_contact", admin.getAd_contact());
				request.getSession().setAttribute("ad_email", admin.getAd_email());
				request.getSession().setAttribute("ad_division", admin.getAd_division());
				request.getSession().setAttribute("ad_etc", admin.getAd_etc());				//슈퍼관리자 구분을 위해 etc 칼럼 사용 필드값은 super | 아닐 경우 일반 구분
				request.getSession().setMaxInactiveInterval(60*30);
				mav.setViewName("redirect:/boardList?board_division=notice");
			}
		}else {
			/*사용자 로그인*/	
			if (admin == null) {															//admin 정보가 없을때
				mav.setViewName("cms/cms_main");
				mav.addObject("result", "fail");
			}else if(admin.getAd_auth().equals("0")){										//회원가입 승인이 안되었을때
				mav.setViewName("cms/cms_main");
				mav.addObject("result", "fail_auth");
			}else if(admin.getAd_division().equals("9")){									//관리자가 사용자로 접속시도할 경우 |  9:관리자, 나머지는 사용자
				mav.setViewName("cms/cms_main");
				mav.addObject("result", "fail_adminerr");
			}else{																			//위 조건 충족 시 관리자 정보를 세션에 담는다.
				request.getSession().setAttribute("ad_id", map.get("ad_id"));
				request.getSession().setAttribute("ad_seq", admin.getAd_seq());
				request.getSession().setAttribute("ad_name", admin.getAd_name());
				request.getSession().setAttribute("ad_contact", admin.getAd_contact());
				request.getSession().setAttribute("ad_email", admin.getAd_email());
				request.getSession().setAttribute("ad_division", admin.getAd_division());
				request.getSession().setAttribute("ad_etc", admin.getAd_etc());				//슈퍼관리자 구분을 위해 etc 칼럼 사용 필드값은 super | 아닐 경우 일반 구분
				request.getSession().setMaxInactiveInterval(60*30);
				mav.setViewName("redirect:/boardList?board_division=notice");
				}
		}
		return mav;
		
	}
	//회원가입 액션
	@RequestMapping(value="/sign_form_insert", method = RequestMethod.POST)
	public String sign_form_insert(CommandMap commandmap, Admin admin) throws Exception {

			admin.setAd_update_id(admin.getAd_id());
			
			as.insert(admin);

		return "cms/cms_main";
	}
	//관리자 정보 수정 폼
	@RequestMapping(value = "/adminUpdate")
	public String adminUpdate(@RequestParam Map<String, Object> paramMap, Admin admin, Model model, HttpServletRequest request, HttpSession session) {

		int ad_seq = admin.getAd_seq(); 
		
		String division = "my";	//내정보수정으로 수정창 진입과 사원관리에서 진입 구분을 위해 추가
        model.addAttribute("division", division);
		model.addAttribute("admin_info", as.admin_read(admin));
		model.addAttribute("authList", as.authList(ad_seq));
		model.addAttribute("result", paramMap.get("result"));
		
		return "admin/adminUpdate";
	}
	//관리자 정보 공통 수정액션
	@RequestMapping(value = "/admin_modify_action", method=RequestMethod.POST) 
	public String admin_modify_action(Admin admin, Authority authority, Model model, HttpServletRequest request, HttpSession session, String division, String pwCheck, String ad_password, String ad_passwordChk, int ad_seq, String[] con_division) {
		
		Object ss_id = session.getAttribute("ad_id");
	    String session_id = ss_id.toString();
	    Object ss_seq = session.getAttribute("ad_seq");
	    String session_seq = ss_seq.toString();
		
		admin.setAd_update_id(session_id);
		
		as.admin_update(admin);
		as.authDelete(authority.getAd_seq());				//게시글 사용 권한 변경을 위해 삭제처리로 초기화
		
		if(con_division != null){
			
			for (int i = 0; i < con_division.length; ++i) {	//접속 권한 체크 된 값들을 저장
				authority.setAd_id(admin.getAd_id());
				authority.setCon_division(con_division[i]);
				authority.setAuthority("1");
				as.authInsert(authority);
			}
		}

		if(pwCheck.equals("1")) {
		
			boolean result = as.passwordCheck(admin);
			if(result == true){
				if(!ad_password.equals(ad_passwordChk)){
					return "redirect:/adminUpdate?ad_seq=" + ad_seq + "&result=fail_id";
				}
				as.password_update(admin);
				return "redirect:/adminUpdate?ad_seq=" + ad_seq + "&result=sucess";
				
			}else { 
				return "redirect:/adminUpdate?ad_seq=" + ad_seq + "&result=fail";
			}
		
		}else{
			String seq = Integer.toString(ad_seq);
			if(seq.equals(session_seq)) {
				return "redirect:/adminUpdate?ad_seq=" + session_seq + "&result=sucess";
			}else{
				return "redirect:/adminList?result=sucess";	
			}
		}
	}
	//관리자 목록 및  회원가입 대기자 조회폼
		@RequestMapping(value = "/adminList")
	public String adminList(@RequestParam Map<String, Object> paramMap, Model model, HttpSession session) {
		
		//조회 하려는 페이지
        int startPage = (!"".equals(paramMap.get("startPage")) && paramMap.get("startPage")!=null?Integer.parseInt(paramMap.get("startPage").toString()):1);

        //한페이지에 보여줄 리스트 수
        int visiblePages = (!"".equals(paramMap.get("visiblePages")) && paramMap.get("visiblePages")!=null?Integer.parseInt(paramMap.get("visiblePages").toString()):10);
        //일단 전체 건수를 가져온다.
        int totalCnt = as.employee_cnt(paramMap);
        
        //아래 1,2는 실제 개발에서는 class로 빼준다. (여기서는 이해를 위해 직접 적음)
        //1.하단 페이지 네비게이션에서 보여줄 리스트 수를 구한다.
        BigDecimal decimal1 = new BigDecimal(totalCnt);
        BigDecimal decimal2 = new BigDecimal(visiblePages);
        BigDecimal totalPage = decimal1.divide(decimal2, 0, BigDecimal.ROUND_UP);
        
        //int allCount = boardService.getallCount(paramMap);
 
        int startLimitPage = 0;
        //2.mysql limit 범위를 구하기 위해 계산
        if(startPage==1){
            startLimitPage = 0;
        }else{
            startLimitPage = (startPage-1)*visiblePages;
        }
        
        //회원가입 대기자 조회
        int waitCnt = as.wait_cnt(paramMap);
        
        paramMap.put("start", startLimitPage);
        paramMap.put("end", visiblePages);
        
        String division = "admin";
        model.addAttribute("division", division);      
        
        model.addAttribute("startPage", startPage+"");//현재 페이지      
        model.addAttribute("totalCnt", totalCnt);//전체 게시물수
        model.addAttribute("totalPage", totalPage);//페이지 네비게이션에 보여줄 리스트 수
        model.addAttribute("waitCnt", waitCnt);//회원가입대기자 수
        
        
		model.addAttribute("employee_list", as.employee_list(paramMap)); //사원목록 리스트
		model.addAttribute("wait_list", as.wait_list(paramMap)); //회원가입 대기자 리스트
		model.addAttribute("result", paramMap.get("result"));
		
		return "admin/adminList";
	}
	//관리자 삭제
  	@RequestMapping(value = "/admin_delete" , method = RequestMethod.GET)
  	public String admin_delete(int[] ad_seq, HttpServletRequest request) {
  		for (int i = 0; i < ad_seq.length; i++) {
  			
  		as.authDelete(ad_seq[i]);
  		as.employee_delete(ad_seq[i]);
  		}
  		
  		return "redirect:/adminList";
  		
  	}
  	//관리자 승인(회원가입)
  	@RequestMapping(value = "/wait_admit" , method = RequestMethod.GET)
  	public String wait_admit(Admin admin, int[] chk, String[] ad_rank, String[] ad_hiredate, HttpServletRequest request) {
  	
  		
  		for (int i = 0; i < chk.length; i++) {
  		
  					admin.setAd_seq(chk[i]);
  					//직급 및 입사일은 필요시에 사용 할 것 2019-02-11
  					/*admin.setAd_rank(ad_rank[i]);*/
					/*admin.setAd_hiredate(ad_hiredate[i]);*/

					as.wait_admit(admin);
  		}
  		return "redirect:/adminList";
  		
  	}
  	//관리자 승인거절(회원가입)
  	@RequestMapping(value = "/wait_delete" , method = RequestMethod.GET)
  	public String wait_delete(int[] chk, HttpServletRequest request) {
  		for (int i = 0; i < chk.length; i++) {
  			
  		
  		as.employee_delete(chk[i]);
  		}
  		
  		return "redirect:/adminList";
  		
  	}
	//로그아웃
	@RequestMapping(value = "/logout") 
	public String logout(HttpServletRequest request) {

		return "admin/logout";
	}
    //로그아웃		
	@RequestMapping(value = "/mainMove") 		
	public String mainMove(HttpServletRequest request) {		
			
		return "admin/mainMove";		
	}
	//공지사항 목록
  	@RequestMapping(value = "/boardList") 
  	public String boardList(@RequestParam Map<String, Object> paramMap, Model model, HttpSession session, Board board) {
  		
  		//조회 하려는 페이지
          int startPage = (!"".equals(paramMap.get("startPage")) && paramMap.get("startPage")!=null?Integer.parseInt(paramMap.get("startPage").toString()):1);

          //한페이지에 보여줄 리스트 수
          int visiblePages = (!"".equals(paramMap.get("visiblePages")) && paramMap.get("visiblePages")!=null?Integer.parseInt(paramMap.get("visiblePages").toString()):10);
          //일단 전체 건수를 가져온다.
          int totalCnt = boardService.board_cnt(paramMap);
          
          //아래 1,2는 실제 개발에서는 class로 빼준다. (여기서는 이해를 위해 직접 적음)
          //1.하단 페이지 네비게이션에서 보여줄 리스트 수를 구한다.
          BigDecimal decimal1 = new BigDecimal(totalCnt);
          BigDecimal decimal2 = new BigDecimal(visiblePages);
          BigDecimal totalPage = decimal1.divide(decimal2, 0, BigDecimal.ROUND_UP);
          
          //int allCount = boardService.getallCount(paramMap);
   
          int startLimitPage = 0;
          //2.mysql limit 범위를 구하기 위해 계산
          if(startPage==1){
              startLimitPage = 0;
          }else{
              startLimitPage = (startPage-1)*visiblePages;
          }
          
          paramMap.put("start", startLimitPage);
          paramMap.put("end", visiblePages);
          
          model.addAttribute("startPage", startPage+"");//현재 페이지      
          model.addAttribute("totalCnt", totalCnt);//전체 게시물수
          model.addAttribute("totalPage", totalPage);//페이지 네비게이션에 보여줄 리스트 수
          model.addAttribute("sch_value", paramMap.get("sch_value"));
          model.addAttribute("sch_type", paramMap.get("sch_type"));
          
//          String board_division = board.getBoard_division();
          
  		model.addAttribute("board_division", paramMap.get("board_division"));
  		model.addAttribute("board_list", boardService.board_list(paramMap));
  		
  		//-------------------------------------------------------------
  		//컨텐츠 허용 권한을 위해 추가 함 2019-03-05
  		//권한을 막을 곳에 해당 소스 삽입 후 get방식으로 넘겨 주면 됨
  		//ex)boardList?board_division=sample
  		//jsp단에서는  <c:if test="${auth == '1'}" /> 이런식으로 사용함 | 1:허용 , 1이 아니면 허용 X  
  		Map<String, Object> paraAuth = new HashMap<String, Object>();
        paraAuth.put("ad_id", session.getAttribute("ad_id"));
        paraAuth.put("con_division", paramMap.get("board_division"));
        String auth = as.getAuth(paraAuth);
        model.addAttribute("auth", auth);
        //-------------------------------------------------------------
        
  		return "board/boardList";
  	}
  	//공통 CMS 게시판 작성 폼
	@RequestMapping(value = "/boardWrite")
	public String boardWrite(@RequestParam Map<String, Object>paramMap, Model model, Board board) {
		
		model.addAttribute("board_division", paramMap.get("board_division"));
		
		return "board/boardWrite";
	}
	//공통 CMS 게시판 수정 폼
	@RequestMapping(value = "/boardUpdate")
	public String boardUpdate(@RequestParam Map<String, Object> paramMap, Model model, Board board, HttpSession session) {
	   model.addAttribute("board_division", paramMap.get("board_division"));
       model.addAttribute("file_list",boardFileService.file_list(paramMap));
	   model.addAttribute("board_update",boardService.board_read(board));
	   
	   //-------------------------------------------------------------
 		//컨텐츠 허용 권한을 위해 추가 함 2019-03-05
 		//권한을 막을 곳에 해당 소스 삽입 후 get방식으로 넘겨 주면 됨
 		//ex)boardList?board_division=sample
 		//jsp단에서는  <c:if test="${auth == '1'}" /> 이런식으로 사용함 | 1:허용 , 1이 아니면 허용 X  
	   Map<String, Object> paraAuth = new HashMap<String, Object>();
       paraAuth.put("ad_id", session.getAttribute("ad_id"));
       paraAuth.put("con_division", paramMap.get("board_division"));
       String auth = as.getAuth(paraAuth);
       model.addAttribute("auth", auth);
       //-------------------------------------------------------------
		
	   return "board/boardUpdate";
	}
	//공통 CMS 게시판 작성 액션
	@RequestMapping(value="/boardWriteAction", method=RequestMethod.POST)
	public String boardWriteAction(@ModelAttribute Board board, Fileup fileup, HttpServletRequest request, Model model, HttpSession session)throws Exception{
				
				String board_division =  board.getBoard_division();
				
			    	Object objss_id = session.getAttribute("ad_id");
			    	String session_id = objss_id.toString();
			    	if (board.getBoard_notice() == null) {					
						board.setBoard_notice("0");
					}
			    	board.setBoard_register_id(session_id);
			    	board.setBoard_use_yn("Y");
					board.setBoard_hit(0);
					board.setBoard_update_id(session_id);
					board.setBoard_etc("etc");

				boardService.board_insert(board);
				
				board = boardService.board_read(board);
				
		        BoardFile boardFile = new BoardFile();
		        boardFile.setBoard_seq(board.getBoard_seq());
		        boardFile.setFile_register_id(board.getBoard_register_id());
		        boardFile.setFile_update_id(board.getBoard_register_id());
		        boardFile.setFile_use_yn("Y");
		        
		        //파일
		        Calendar cal = Calendar.getInstance()  ;
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmSS");
		        String time = dateFormat.format(cal.getTime());
		        
		        List<MultipartFile> files = fileup.getUploadfile();
				System.out.println("File ----->" + files);
				if (null != files && files.size() > 0) {
					
					
					for (MultipartFile multipartFile : files) {
						System.out.println("----------------->?" + multipartFile);
						if (!"".equals(multipartFile.getOriginalFilename()) && multipartFile.getSize() > 0) {
						
							System.out.println("file = " + multipartFile.getOriginalFilename() + "/" + multipartFile.getSize());
							// 상대경로 
							String file_path = request.getSession().getServletContext().getRealPath("/");
							
							String file_ori_name = multipartFile.getOriginalFilename();
							String file_sub_name = time + "-" + UUID.randomUUID().toString() +"_" +file_ori_name;
							String attach_path = "";
							
								attach_path = "resources/uploadFile/"+board_division+"/";
							
								
							File f = new File(file_path + attach_path + file_sub_name);
							System.out.println("===========자료실 파일업로드 실제 Path=========" + f);
							
							if(!f.exists()) {
								f.mkdirs();
							}
							//	이력서 model에 파일명,주소 저장
							//         파일명에서 확장자 추출 
							String filename = file_ori_name;
							int fileLen = filename.length();
							int lastDot = filename.lastIndexOf('.');
							String fileExt = filename.substring(lastDot, fileLen).toLowerCase();
							boardFile.setFile_ext_name(fileExt);
							boardFile.setFile_ori_name(file_ori_name);
							boardFile.setFile_sub_name(file_sub_name);
							boardFile.setFile_path("/" + attach_path);
							
							long fsize = multipartFile.getSize();
							String Fsize = String.valueOf(fsize);

					        System.out.println(" size = " + Fsize + " bytes");
					 
							
							boardFile.setFile_size(Fsize);

							System.out.println("확장명 : " + fileExt);
							
							boardFileService.file_insert(boardFile);
							
							try {
								
								multipartFile.transferTo(f);
							} catch (IllegalStateException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							try { 						
								
							} catch (Exception e) {
								model.addAttribute("msg", "다시 입력하세요.");
							}
							
						}
					}
					
				}

		        	return "redirect:/boardList?board_division=" + board.getBoard_division();
		    }
	//공통 CMS 게시판 수정 액션
	@RequestMapping(value="/boardUpdateAction", method=RequestMethod.POST)
	public String boardUpdateAction(@ModelAttribute Board board, Fileup fileup, HttpServletRequest request, Model model,  String[] file_key, String[] flag/*, String[] fName*/, HttpSession session)throws Exception{
				
					String board_division =  board.getBoard_division();
					
					Object objss_id = session.getAttribute("ad_id");
				    String session_id = objss_id.toString();
					
				    if (board.getBoard_notice() == null) {					
						board.setBoard_notice("0");
					}
					board.setBoard_update_id(session_id);

					boardService.board_update(board);
							
			        BoardFile boardFile = new BoardFile();
			        boardFile.setBoard_seq(board.getBoard_seq());
			        boardFile.setFile_update_id(session_id);
			        boardFile.setFile_use_yn("Y");
			        
			        //파일
			        Calendar cal = Calendar.getInstance()  ;
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmSS");
			        String time = dateFormat.format(cal.getTime());
			        
			        List<MultipartFile> files = fileup.getUploadfile();
					
					//List<String> fileNames = new ArrayList<String>();
					if (null != files && files.size() > 0) {
						
						
						for (MultipartFile multipartFile : files) {
							if (!"".equals(multipartFile.getOriginalFilename()) && multipartFile.getSize() > 0) {
							
								// 상대경로 
								String file_path = request.getSession().getServletContext().getRealPath("/");
								String file_ori_name = multipartFile.getOriginalFilename();
								String file_sub_name = time + "-" + UUID.randomUUID().toString() +"_" +file_ori_name;
								
								String attach_path = "";
								attach_path = "resources/uploadFile/"+board_division+"/";
								
								File f = new File(file_path + attach_path + file_sub_name);
								
								System.out.println("===========자료실 파일업로드 실제 Path=========" + f);
								
								if(!f.exists())
									f.mkdirs();
								
								//	이력서 model에 파일명,주소 저장
								//         파일명에서 확장자 추출 
								String filename = file_ori_name;
								int fileLen = filename.length();
								int lastDot = filename.lastIndexOf('.');
								String fileExt = filename.substring(lastDot, fileLen).toLowerCase();
								boardFile.setFile_ext_name(fileExt);
								boardFile.setFile_ori_name(file_ori_name);
								boardFile.setFile_sub_name(file_sub_name);
								boardFile.setFile_path("/" + attach_path);
								boardFile.setFile_register_id(session_id);
								long fsize = multipartFile.getSize();
								String file_size = String.valueOf(fsize);

						        System.out.println(" size = " + file_size + " bytes");
								boardFile.setFile_size(file_size);
								System.out.println("확장명 : " + fileExt);
								
								boardFileService.file_insert(boardFile);
								
								try {
									multipartFile.transferTo(f);
								} catch (IllegalStateException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
								try { 						
								} catch (Exception e) {
									model.addAttribute("msg", "다시 입력하세요.");
								}
								
							}
						}
						
						
					}
					if(file_key != null) {
			    		
			    		for(int i=0; i<file_key.length ; i++) {
			    			
			    			System.out.println("===========fileKey==============>" + file_key[i]);
			    			System.out.println("===========flag==============>" + flag[i]);
			        		//flag가 D인건 삭제. 데이터도 삭제, 파일도 삭제.
			    			if("D".equals(flag[i])) {
			    				boardFile.setFile_seq(Integer.parseInt(file_key[i]));
			    				boardFile.setFile_update_id(session_id);
			    				
			    				boardFileService.file_updateform_delete(boardFile);
			    			}
			    		}
			    		}
					boardService.board_update(board);
					
					return "redirect:/boardList?board_division=" + board.getBoard_division();
		    }
	//공통 CMS 게시판 삭제 액션
	@RequestMapping(value = "/board_delete" , method = RequestMethod.GET)
	public String board_delete(Board board, BoardFile boardFile, int[] board_seq, HttpServletRequest request, HttpSession session) {
			
			String board_division =  board.getBoard_division();
			Object objss_id = session.getAttribute("ad_id");
		    String session_id = objss_id.toString();
		
			for (int i = 0; i < board_seq.length; i++) {
				
			board.setBoard_update_id(session_id);
			board.setBoard_seq(board_seq[i]);
			
			boardService.board_delete(board);
			
			boardFile.setFile_update_id(session_id);
			boardFile.setBoard_seq(board_seq[i]);
			
			boardFileService.file_delete(boardFile);
			}
				return "redirect:/boardList?board_division=" + board_division;
		}
	//공통 CMS 게시판 다운로드 액션
	@RequestMapping("/boardFileDown")
    private void boardFileDown(Board board, String file_name, String file_seq, String board_division, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("UTF-8");
   		
   		//자료실 다운 액션
   		try {
			 /*상대경로 */
   		String file_path = request.getSession().getServletContext().getRealPath("/");
   		System.out.println("file_path ==============>? ? ? " + file_path);
   		String attach_path = "";
   		
   		attach_path = "resources/uploadFile/"+board_division+"/";

   		String savePath = file_path+attach_path;
   		String fileName = file_name;
   		//실제 내보낼 파일명
   		String oriFileName = file_name;
   		String oriFileNames = oriFileName.substring(oriFileName.indexOf("_")+1);
   		
   		InputStream in = null;
   		OutputStream os = null;
   		File file = null;
   		boolean skip = false;
   		String client = "";
   		
   		//파일을 읽어 스트림에 담기
   		try {
				file = new File(savePath, fileName);
				in = new FileInputStream(file);
			} catch (FileNotFoundException fe) {
				skip = true;
			}
   		
   		client = request.getHeader("User-Agent");
   		
   		//파일 다운로드 헤더 지정 
           response.reset();
           response.setContentType("application/octet-stream");
           response.setHeader("Content-Description", "JSP Generated Data");
           
           if (!skip) {
               // IE
               if (client.indexOf("MSIE") != -1) {
                   response.setHeader("Content-Disposition", "attachment; filename=\""
                           + java.net.URLEncoder.encode(oriFileNames, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                   
                   // IE 11 이상.
               } else if (client.indexOf("Trident") != -1) {
                   response.setHeader("Content-Disposition", "attachment; filename=\""
                           + java.net.URLEncoder.encode(oriFileNames, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
               } else {
                   // 한글 파일명 처리
                   response.setHeader("Content-Disposition",
                           "attachment; filename=\"" + new String(oriFileNames.getBytes("UTF-8"), "ISO8859_1") + "\"");
                   response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
               }
               response.setHeader("Content-Length", "" + file.length());
               os = response.getOutputStream();
               byte b[] = new byte[(int) file.length()];
               int leng = 0;
               while ((leng = in.read(b)) > 0) {
                   os.write(b, 0, leng);
                   boardFileService.file_hit(file_seq);
               }
           } else {
               response.setContentType("text/html;charset=UTF-8");
               System.out.println("파일을 찾을 수 없습니다.");
           }
           in.close();
           os.close();
       } catch (Exception e) {
           System.out.println("ERROR : " + e.getMessage());
       }
   		
   	}
	
}