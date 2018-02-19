package com.iot.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.SqlService;
import com.iot.spring.vo.SqlVO;



@Controller
@RequestMapping("/sql")
public class SqlRunController {
	@Autowired
	private SqlService sqls;
	
	private static final Logger log = LoggerFactory.getLogger(SqlRunController.class);
	
	@RequestMapping(value="/run", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> sqlRun(
			
			@RequestParam Map<String,Object> map, 
			HttpSession hs
			){

		log.info("query=>{}", map);
		SqlVO sql = new SqlVO();
		sql.setSqlTa((String) map.get("sqlTa"));
		sql.setSelectDatabase((String)map.get("database"));
		log.info("test=>{}", (String)map.get("database"));
		List<Map<String, Object>> result = sqls.sqlRun(sql, hs);
		map.put("result", result);

		log.info("test=>{}", result);
		return map;
	}
}
