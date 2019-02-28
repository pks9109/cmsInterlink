package com.homepage.cmsInterlink.model;

import java.sql.Date;

public class BoardFile {

	private int file_seq;				//파일 키 , primary key
	private int board_seq;				//게시글 키, primary key
	private String file_ori_name;		//파일 원본 이름
	private String file_sub_name;		//파일 서브 이름
	private String file_ext_name;		//파일 확장명
	private String file_size;			//파일 크기
	private String file_hit;			//파일 다운로드 수
	private String file_path;			//파일 경로
	private String file_use_yn;			//파일 사용 유무 : 사용:y, 미사용:n
	private String file_register_id;	//파일 등록 아이디
	private Date file_register_date;	//파일 등록 일
	private String file_update_id;		//파일 수정 아이디
	private Date file_update_date;		//파일 수정 일
	private String file_etc;			//파일 기타 : 미사용
	
	public int getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(int file_seq) {
		this.file_seq = file_seq;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getFile_ori_name() {
		return file_ori_name;
	}
	public void setFile_ori_name(String file_ori_name) {
		this.file_ori_name = file_ori_name;
	}
	public String getFile_sub_name() {
		return file_sub_name;
	}
	public void setFile_sub_name(String file_sub_name) {
		this.file_sub_name = file_sub_name;
	}
	public String getFile_ext_name() {
		return file_ext_name;
	}
	public void setFile_ext_name(String file_ext_name) {
		this.file_ext_name = file_ext_name;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getFile_hit() {
		return file_hit;
	}
	public void setFile_hit(String file_hit) {
		this.file_hit = file_hit;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_use_yn() {
		return file_use_yn;
	}
	public void setFile_use_yn(String file_use_yn) {
		this.file_use_yn = file_use_yn;
	}
	public String getFile_register_id() {
		return file_register_id;
	}
	public void setFile_register_id(String file_register_id) {
		this.file_register_id = file_register_id;
	}
	public Date getFile_register_date() {
		return file_register_date;
	}
	public void setFile_register_date(Date file_register_date) {
		this.file_register_date = file_register_date;
	}
	public String getFile_update_id() {
		return file_update_id;
	}
	public void setFile_update_id(String file_update_id) {
		this.file_update_id = file_update_id;
	}
	public Date getFile_update_date() {
		return file_update_date;
	}
	public void setFile_update_date(Date file_update_date) {
		this.file_update_date = file_update_date;
	}
	public String getFile_etc() {
		return file_etc;
	}
	public void setFile_etc(String file_etc) {
		this.file_etc = file_etc;
	}
	@Override
	public String toString() {
		return "BoardFile [file_seq=" + file_seq + ", board_seq=" + board_seq + ", file_ori_name=" + file_ori_name
				+ ", file_sub_name=" + file_sub_name + ", file_ext_name=" + file_ext_name + ", file_size=" + file_size
				+ ", file_hit=" + file_hit + ", file_path=" + file_path + ", file_use_yn=" + file_use_yn
				+ ", file_register_id=" + file_register_id + ", file_register_date=" + file_register_date
				+ ", file_update_id=" + file_update_id + ", file_update_date=" + file_update_date + ", file_etc="
				+ file_etc + "]";
	}
	
	
	
}
