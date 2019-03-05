package com.homepage.cmsInterlink.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.homepage.cmsInterlink.model.Admin;
import com.homepage.cmsInterlink.model.Authority;

public interface AdminService {
	int insert(Admin admin);
	int idcheck(String id);
	Admin selectUserInfo(Map<String, Object> map);
	Admin admin_read(Admin admin); //관리자정보 읽기
	void admin_update(Admin admin); //관리자정보 수정
	boolean passwordCheck(Admin admin); //비밀번호확인체크
	void password_update(Admin admin); //관리자패스워드 수정
	List<Admin> employee_list(Map<String, Object> paramMap); //사원관리 목록 출력
	public int employee_cnt(Map<String, Object> paramMap); //사원관리 전체 페이지 수
	public int wait_cnt(Map<String, Object> paramMap); //회원가입 대기자 수
	public int employee_delete(int ad_seq);
	List<Admin> wait_list(Map<String, Object> paramMap);
	public int wait_admit(Admin admin);
	public int authDelete(int ad_seq);
	public int authInsert(Authority authority);
	List<Authority> authList(int ad_seq);
	String getAuth(Map<String, Object> paraAuth);
}
