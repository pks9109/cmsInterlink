package com.homepage.cmsInterlink.dao;

import java.util.List;
import java.util.Map;

import com.homepage.cmsInterlink.model.Board;
import com.homepage.cmsInterlink.model.BoardFile;

public interface BoardFileDao {

	public void file_insert(BoardFile boardFile);

	public List<BoardFile> file_list(Map<String, Object> paramMap);

	public void file_delete(BoardFile boardFile);

	public void file_updateform_delete(BoardFile boardFile);

	public void file_hit(String file_seq);

}
