package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookVo guestbookVo;
	private UserVo userVo;
	private GuestbookRepository guestbookRepository;
	private UserRepository userRepository;
	
	public void deleteByNo(Long no) {
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		guestbookRepository.delete(vo);
	}
	
	public void add(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
}
