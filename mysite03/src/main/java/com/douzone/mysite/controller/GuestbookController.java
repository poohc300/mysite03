package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.service.UserService;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private UserService userService;
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String get() {
		return "guestbook/index";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String insert() {
		return "guestbook/add";
		
	}
	
	@PostMapping(value="/delete/{no}")
	@ResponseBody
	public String deleteByNo(
			@PathVariable("no") Long no,
			@RequestParam(value="pasword",
			required=true,
			defaultValue="") String password
			) {
		
		return "guestbook/delete";
	}
}
