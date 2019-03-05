package com.homepage.cmsInterlink.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homepage.cmsInterlink.dao.AdminDao;
import com.homepage.cmsInterlink.model.Admin;
import com.homepage.cmsInterlink.model.Authority;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao ad;

	@Override
	public int insert(Admin admin) {
		return ad.insert(admin);
	}

	@Override
	public int idcheck(String id) {
		return ad.idcheck(id);
	}

	@Override
	public Admin selectUserInfo(Map<String, Object> map) {
		return ad.selectUserInfo(map);
	}

	@Override
	public Admin admin_read(Admin admin) {
		return ad.admin_read(admin);
	}

	@Override
	public void admin_update(Admin admin) {
		ad.admin_update(admin);
	}

	@Override
	public boolean passwordCheck(Admin admin) {
		return ad.passwordCheck(admin);
		
	}

	@Override
	public void password_update(Admin admin) {
		ad.password_update(admin);
		
	}

	@Override
	public List<Admin> employee_list(Map<String, Object> paramMap) {
		return ad.employee_list(paramMap);
	}

	@Override
	public int employee_cnt(Map<String, Object> paramMap) {
		return ad.employee_cnt(paramMap);
	}

	@Override
	public int wait_cnt(Map<String, Object> paramMap) {
		return ad.wait_cnt(paramMap);
	}

	@Override
	public int employee_delete(int ad_seq) {
		return ad.employee_delete(ad_seq);
	}

	@Override
	public List<Admin> wait_list(Map<String, Object> paramMap) {
		return ad.wait_list(paramMap);
	}

	@Override
	public int wait_admit(Admin admin) {
		return ad.wait_admit(admin);
		
	}

	@Override
	public int authDelete(int ad_seq) {
		return ad.authDelete(ad_seq);
	}

	@Override
	public int authInsert(Authority authority) {
		return ad.authInsert(authority);
	}

	@Override
	public List<Authority> authList(int ad_seq) {
		return ad.authList(ad_seq);
	}

	@Override
	public String getAuth(Map<String, Object> paraAuth) {
		return ad.getAuth(paraAuth);
	}

	
	
}
