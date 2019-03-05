package com.homepage.cmsInterlink.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.homepage.cmsInterlink.model.Admin;
import com.homepage.cmsInterlink.model.Authority;

@Repository
public class AdminDaoImpl implements AdminDao{
	@Autowired
	private SqlSessionTemplate sst;
	@Autowired
    private SqlSession sqlSession;

	@Override
	public int insert(Admin admin) {
		return sst.insert("admins.insert", admin);
	}

	@Override
	public int idcheck(String id) {
		return sst.selectOne("admins.idcheck", id);
	}

	@Override
	public Admin selectUserInfo(Map<String, Object> map) {
		return sst.selectOne("admins.selectUserInfo", map);
	}

	@Override
	public Admin admin_read(Admin admin) {
		return sst.selectOne("admins.admin_read", admin);
	}

	@Override
	public void admin_update(Admin admin) {
		sst.update("admins.admin_update", admin);
	}

	@Override
	public boolean passwordCheck(Admin admin) {
		boolean result = false;
		
        int count = sqlSession.selectOne("admins.passwordCheck", admin);

        if(count == 1) result = true;
        
        return result;
	}

	@Override
	public void password_update(Admin admin) {
		sst.update("admins.password_update", admin);
		
	}

	@Override
	public List<Admin> employee_list(Map<String, Object> paramMap) {
		return sst.selectList("admins.employee_list", paramMap);
	}

	@Override
	public int employee_cnt(Map<String, Object> paramMap) {
		return sst.selectOne("admins.employee_cnt", paramMap);
	}

	@Override
	public int wait_cnt(Map<String, Object> paramMap) {
		return sst.selectOne("admins.wait_cnt", paramMap);
	}

	@Override
	public int employee_delete(int ad_seq) {
		return sst.delete("admins.employee_delete", ad_seq);
	}

	@Override
	public List<Admin> wait_list(Map<String, Object> paramMap) {
		return sst.selectList("admins.wait_list", paramMap);
	}

	@Override
	public int wait_admit(Admin admin) {
		return sst.update("admins.wait_admit", admin);
	}

	@Override
	public int authDelete(int ad_seq) {
		return sst.delete("admins.authDelete", ad_seq);
	}

	@Override
	public int authInsert(Authority authority) {
		return sst.insert("admins.authInsert", authority);
	}

	@Override
	public List<Authority> authList(int ad_seq) {
		return sst.selectList("admins.authList", ad_seq);
	}

	@Override
	public String getAuth(Map<String, Object> paraAuth) {
		return sst.selectOne("admins.getAuth", paraAuth);
	}
	
	
	
	
	

}
