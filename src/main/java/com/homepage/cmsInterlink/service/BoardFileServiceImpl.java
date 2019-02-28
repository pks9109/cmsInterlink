package com.homepage.cmsInterlink.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.homepage.cmsInterlink.dao.BoardFileDao;
import com.homepage.cmsInterlink.model.Board;
import com.homepage.cmsInterlink.model.BoardFile;

@Service("boardFileService")
public class BoardFileServiceImpl implements BoardFileService{
	
	@Resource(name="boardFileDao")
	private BoardFileDao boardFileDao;

	@Override
	public void file_insert(BoardFile boardFile) {
		boardFileDao.file_insert(boardFile);
		
	}

	@Override
	public List<BoardFile> file_list(Map<String, Object> paramMap) {
		return boardFileDao.file_list(paramMap);
	}


	@Override
	public void file_delete(BoardFile boardFile) {
		boardFileDao.file_delete(boardFile);
		}

	@Override
	public void file_updateform_delete(BoardFile boardFile) {
		boardFileDao.file_updateform_delete(boardFile);
		
	}

	@Override
	public void file_hit(String file_seq) {
		boardFileDao.file_hit(file_seq);
	}


	
}
