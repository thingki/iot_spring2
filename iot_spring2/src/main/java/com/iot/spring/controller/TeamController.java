package com.iot.spring.controller;

import java.util.*;

import javax.servlet.http.*;

import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller

@RequestMapping("/team")
public class TeamController {
	private static final Logger log = LoggerFactory.getLogger(TeamController.class);
	
	@RequestMapping("/movie")
	public @ResponseBody Map<String, Object> movie(Map <String,Object> map){
	
		log.info("dd={}", map);
		
		return null;
	}
}
