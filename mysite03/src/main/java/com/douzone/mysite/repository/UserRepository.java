package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
		
	public boolean insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo) == 1;
	}

	public List<UserVo> findByEmailAndPassword(UserVo vo) {
	
		return sqlSession.selectList("user.findByEmailAndPassword", vo);
	}	

	public List<UserVo> findByNo(UserVo vo) {
		
		return sqlSession.selectList("user.findByNo", vo);
	}

	public boolean update(UserVo vo) {
		boolean result = sqlSession.update("user.update", vo) == 1;
		
		return result;
	}
}