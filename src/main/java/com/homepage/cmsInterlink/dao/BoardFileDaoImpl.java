package com.homepage.cmsInterlink.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.homepage.cmsInterlink.model.Board;
import com.homepage.cmsInterlink.model.BoardFile;

@Repository("boardFileDao")
public class BoardFileDaoImpl implements BoardFileDao {
	
	@Inject
    SqlSession SqlSession;

	@Override
	public void file_insert(BoardFile boardFile) {
		SqlSession.insert("boardFiles.file_insert", boardFile);
		
	}

	@Override
	public List<BoardFile> file_list(Map<String, Object> paramMap) {
		return SqlSession.selectList("boardFiles.file_list", paramMap);
	}


	@Override
	public void file_delete(BoardFile boardFile) {
		SqlSession.update("boardFiles.file_delete", boardFile);
		
	}

	@Override
	public void file_updateform_delete(BoardFile boardFile) {
		SqlSession.update("boardFiles.file_updateform_delete", boardFile);
		
	}

	@Override
	public void file_hit(String file_seq) {
		SqlSession.update("boardFiles.file_hit", file_seq);
		
	}

	
	
	
}