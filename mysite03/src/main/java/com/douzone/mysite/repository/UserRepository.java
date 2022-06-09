package com.douzone.mysite.repository;

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

	public UserVo findByEmailAndPassword(UserVo vo) {
	
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
	}	

	public UserVo findByNo(Long no) {
		
		return sqlSession.selectOne("user.findByNo", no);
	}

	public boolean update(UserVo vo) {
		boolean result = sqlSession.update("user.update", vo) == 1;
		
		return result;
	}
}