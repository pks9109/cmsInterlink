package com.homepage.cmsInterlink.model;

public class Admin {
	private int ad_seq;					//관리자 키 값
	private String ad_id;				//관리자 아이디
	private String ad_password;			//관리자 비밀번호
	private String ad_name;				//관리자 이름
	private String ad_contact;			//관리자 연락처
	private String ad_email;			//관리자 이메일
	private String ad_hiredate;			//관리자 입사 일 : 미사용
	private String ad_register_date;	//관리자 가입 일
	private String ad_update_id;		//관리자 수정 아이디
	private String ad_update_date;		//관리자 수정 일
	private String ad_rank;				//관리자 직급 : 미사용
	private String ad_auth;				//관리자 승인 : 1:승인 , 0:미승인
	private String ad_division;			//관리자 구분 : 0:기본 , 9:전체관리자
	private String ad_etc;				//관리자 기타
	
	private String ad_ori_password;

	public int getAd_seq() {
		return ad_seq;
	}

	public void setAd_seq(int ad_seq) {
		this.ad_seq = ad_seq;
	}

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_password() {
		return ad_password;
	}

	public void setAd_password(String ad_password) {
		this.ad_password = ad_password;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getAd_contact() {
		return ad_contact;
	}

	public void setAd_contact(String ad_contact) {
		this.ad_contact = ad_contact;
	}

	public String getAd_email() {
		return ad_email;
	}

	public void setAd_email(String ad_email) {
		this.ad_email = ad_email;
	}

	public String getAd_hiredate() {
		return ad_hiredate;
	}

	public void setAd_hiredate(String ad_hiredate) {
		this.ad_hiredate = ad_hiredate;
	}

	public String getAd_register_date() {
		return ad_register_date;
	}

	public void setAd_register_date(String ad_register_date) {
		this.ad_register_date = ad_register_date;
	}

	public String getAd_update_id() {
		return ad_update_id;
	}

	public void setAd_update_id(String ad_update_id) {
		this.ad_update_id = ad_update_id;
	}

	public String getAd_update_date() {
		return ad_update_date;
	}

	public void setAd_update_date(String ad_update_date) {
		this.ad_update_date = ad_update_date;
	}

	public String getAd_rank() {
		return ad_rank;
	}

	public void setAd_rank(String ad_rank) {
		this.ad_rank = ad_rank;
	}

	public String getAd_auth() {
		return ad_auth;
	}

	public void setAd_auth(String ad_auth) {
		this.ad_auth = ad_auth;
	}

	public String getAd_division() {
		return ad_division;
	}

	public void setAd_division(String ad_division) {
		this.ad_division = ad_division;
	}

	public String getAd_etc() {
		return ad_etc;
	}

	public void setAd_etc(String ad_etc) {
		this.ad_etc = ad_etc;
	}

	public String getAd_ori_password() {
		return ad_ori_password;
	}

	public void setAd_ori_password(String ad_ori_password) {
		this.ad_ori_password = ad_ori_password;
	}

	@Override
	public String toString() {
		return "Admin [ad_seq=" + ad_seq + ", ad_id=" + ad_id + ", ad_password=" + ad_password + ", ad_name=" + ad_name
				+ ", ad_contact=" + ad_contact + ", ad_email=" + ad_email + ", ad_hiredate=" + ad_hiredate
				+ ", ad_register_date=" + ad_register_date + ", ad_update_id=" + ad_update_id + ", ad_update_date="
				+ ad_update_date + ", ad_rank=" + ad_rank + ", ad_auth=" + ad_auth + ", ad_division=" + ad_division
				+ ", ad_etc=" + ad_etc + ", ad_ori_password=" + ad_ori_password + "]";
	}

	
	
	
}
