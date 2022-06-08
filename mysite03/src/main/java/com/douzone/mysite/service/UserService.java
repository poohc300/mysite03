package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo vo) {
		userRepository.insert(vo);
	}

	public List<UserVo> getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
	}

	public List<UserVo> getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public void updateUser(UserVo vo) {
		userRepository.update(vo);
	}
}