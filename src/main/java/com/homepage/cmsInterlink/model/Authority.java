package com.homepage.cmsInterlink.model;

public class Authority {
	String ad_id;
	int ad_seq;
	String con_division;
	String authority;
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public int getAd_seq() {
		return ad_seq;
	}
	public void setAd_seq(int ad_seq) {
		this.ad_seq = ad_seq;
	}
	public String getCon_division() {
		return con_division;
	}
	public void setCon_division(String con_division) {
		this.con_division = con_division;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "Authority [ad_id=" + ad_id + ", ad_seq=" + ad_seq + ", con_division=" + con_division + ", authority="
				+ authority + "]";
	}
	
	
	
}
