package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	private GuestbookRepository guestbookRepository;
	
	public void join(UserVo vo) {
		userRepository.insert(vo);
	}
	
	public void login(UserVo vo) {
		userRepository.findByEmailAndPassword(vo);
		
	}
	
	public void write(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
	
	public void delete(GuestbookVo vo) {
		guestbookRepository.delete(vo);
	}
	
	
}
