package com.homepage.cmsInterlink.service;


import java.util.List;
import java.util.Map;

import com.homepage.cmsInterlink.model.Board;

public interface BoardService {
	
	/*공통*/
	
	//게시판 작성
	public void board_insert(Board board);                
	//게시판 상세보기
	public Board board_read(Board board);
	//게시판 목록
	List<Board> board_list(Map<String, Object> paramMap);
	//게시판 게시물 수
	public int board_cnt(Map<String, Object> paramMap);
	//게시판 수정
	public void board_update(Board board);
	//게시글 삭제
	public void board_delete(Board board);
	
	/*서브*/
	//추후에 사용 할 예정
	/*
	//메인 다운로드 뷰 출력
	List<Board> download_list(Map<String, Object> paramMap);
	//다운로드 전체 조회수
	public int download_cnt(Map<String, Object> paramMap);
	//다운로드 클릭 시 조회수 증가
	int download_hit(int board_seq); 
*/
	}
