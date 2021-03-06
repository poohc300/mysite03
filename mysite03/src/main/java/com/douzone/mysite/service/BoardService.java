package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<BoardVo> getBoardList(){
		
		return boardRepository.findAll();
	}
	
	public Boolean deleteBoard(Long no, String password) {
		return boardRepository.delete(no, password);
	}
	
	public Boolean addBoard(BoardVo vo) {
		return boardRepository.insert(vo);
	}
}
