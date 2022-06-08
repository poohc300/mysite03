package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.BoardRepositoryException;
import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll(){
		return sqlSession.selectList("board.findAll");
	}
	
	public boolean delete(Long no, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);
		
		return sqlSession.delete("board.delete", map) == 1;
	}
	
	public boolean insert(BoardVo vo) {
		boolean result = sqlSession.insert("board.insert", vo) == 1;
		
		return result;
	}
}
