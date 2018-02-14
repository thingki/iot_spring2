package com.iot.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.MenuInfoService;
import com.iot.spring.vo.MenuInfo;

@Controller
@RequestMapping("/menu")
public class MenuInfoController {
	@Autowired
	private MenuInfoService mis;
	
	private static final Logger log = LoggerFactory.getLogger(MenuInfoController.class);
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getMenuInfoList(Map<String,Object> map, HttpSession hs){
		List<MenuInfo> list = mis.getMenuInfoList();
		hs.setAttribute("menuList", list);
		
		log.info("menu=>{}", list);
		return map;
	}
}
