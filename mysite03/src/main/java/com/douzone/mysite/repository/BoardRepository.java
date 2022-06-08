package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.BoardRepositoryException;
import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private DataSource dataSource;
	
	public List<BoardVo> findAll(){
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = 
					" select no, title, content, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date"+
					" from board "+
					" order by reg_date desc";
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDate(regDate);
				
				list.add(vo);
			}
		} catch(SQLException e) {
			System.out.println("error: "+e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	public boolean delete(BoardVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = 
					" delete from board" +
					" where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
		} catch(SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean insert(BoardVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			
			String sql =
					" insert" +
					"   into board" +
					" values (null, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			// System.out.println("error:" + e);
			throw new BoardRepositoryException(e.toString());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}
}
