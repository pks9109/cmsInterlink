package com.homepage.cmsInterlink.dao;


import java.util.List;
import java.util.Map;

import com.homepage.cmsInterlink.model.Board;

public interface BoardDao {

	public void board_insert(Board board);

	public Board board_read(Board board);

	public List<Board> board_list(Map<String, Object> paramMap);

	public int board_cnt(Map<String, Object> paramMap);

	public void board_update(Board board);

	public void board_delete(Board board);
	
	//추후 필요시 사용
	/*public List<Board> download_list(Map<String, Object> paramMap);

	public int download_cnt(Map<String, Object> paramMap);

	public int download_hit(int board_seq);*/

}
