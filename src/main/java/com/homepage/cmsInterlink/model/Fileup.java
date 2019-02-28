package com.homepage.cmsInterlink.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Fileup {
	private List<MultipartFile> uploadfile; 
	private String fileName;
	
	public List<MultipartFile> getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(List<MultipartFile> uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "Fileup [uploadfile=" + uploadfile + ", fileName=" + fileName + "]";
	}
	
	
	
	
}
