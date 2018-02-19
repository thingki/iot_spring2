package com.iot.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.UserInfoService;
import com.iot.spring.vo.UserInfoVO;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	@Autowired
	private UserInfoService uis;
	
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(UserInfoVO ui, HttpSession hs){
		Map<String, Object> map = new HashMap<String, Object>();
		log.info("ui=>{}", ui);
		if(uis.login(ui, map)) {
			hs.setAttribute("user", map.get("user"));
		}
		return map;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(HttpSession hs){
		log.info("menuList=>{}", hs.getAttribute("menuList"));
		hs.invalidate();
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> join(@RequestBody UserInfoVO ui){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = uis.insertUserInfo(ui);
		map.put("msg", "회원가입실패!");
		map.put("biz", false);
		if(result == 1) {
			map.put("biz", true);
			map.put("msg", "성공!");
		}else if(result==2) {
			map.put("msg", "아이디 중복!");
		}
		return map;
	}
	
	@RequestMapping(value="/checkId", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkUserInfo(@RequestBody UserInfoVO ui, HttpSession hs){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("msg", "아이디 사용가능!");
		map.put("biz", true);
		hs.setAttribute("checkId", ui.getuId());
		if(uis.checkUserInfo(ui)) {
			map.put("biz", false);
			map.put("msg", "아이디가 중복이다!");
		}
		return map;
	}
}
