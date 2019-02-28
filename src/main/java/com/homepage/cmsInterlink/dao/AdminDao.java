package com.homepage.cmsInterlink.dao;

import java.util.List;
import java.util.Map;

import com.homepage.cmsInterlink.model.Admin;

public interface AdminDao {
	int insert(Admin admin);
	int idcheck(String id);
	Admin selectUserInfo(Map<String, Object> map);
	Admin admin_read(Admin admin);
	void admin_update(Admin admin);
	boolean passwordCheck(Admin admin);
	void password_update(Admin admin);
	List<Admin> employee_list(Map<String, Object> paramMap);
	public int employee_cnt(Map<String, Object> paramMap);
	public int wait_cnt(Map<String, Object> paramMap);
	public int employee_delete(int ad_seq);
	List<Admin> wait_list(Map<String, Object> paramMap);
	public int wait_admit(Admin admin);
}
