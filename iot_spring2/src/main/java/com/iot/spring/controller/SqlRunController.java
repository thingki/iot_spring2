package com.iot.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
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
	private SqlService sqlService;
	
	private static final Logger log = LoggerFactory.getLogger(SqlRunController.class);
	
	@RequestMapping(value="/run", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> sqlRun(
			@RequestParam Map<String,Object> map, 
			HttpSession hs
			){
		double timeDuration = System.currentTimeMillis();
		String sqlStr = (String) map.get("sqlTa");
		String[] sqls = sqlStr.trim().split(";");
		List<Object> list = new ArrayList<Object>();
		List<String> msgList= new ArrayList<String>();
		int affectedRow = 0;
		int findLine = 0;
		int warning = 0;
		int queries = 0;
		sqlService.setDatabase(map, hs);
		for(String sql:sqls) {
			sql = sql.trim()+";";
			SqlVO sqlvo = new SqlVO();
			queries++;
			if(sql.indexOf("use")==0) {
				sqlvo.setSqlTa(sql);
				hs.removeAttribute("useDatabase"); 
				sqlService.useDatabaseSql(sqlvo, map, hs);
			}else if(sql.indexOf("select")==0){
				sqlvo.setSqlTa(sql);
				List<Map<String, Object>> selectList = new ArrayList<Map<String, Object>>();
				selectList=sqlService.sqlRun(sqlvo, map, hs);
				findLine=findLine+selectList.size();
				list.add(selectList);
			}else{
				sqlvo.setSqlTa(sql);
				affectedRow=affectedRow+sqlService.updateSqlRun(sqlvo, map, hs);
			}
			msgList.add(sql+"\r\n");
		}	
		timeDuration=System.currentTimeMillis()-timeDuration;
		msgList.add("/* Affected rows: "+affectedRow+"  찾은 행: "+findLine+"  경고: "+warning+"  지속 시간 "+queries+" queries: 0."+timeDuration+"sec. */");
		map.put("msg", msgList);
		map.put("list", list);
		return map;
	}
}
	// "msg" ArrayList<String>
	// "list" ArrayList<Map<String, Object>
	//						제목       리스트내용List<Map<>>



/* 이전 버전(18.02.21 10:54)
@RequestMapping(value="/run", method=RequestMethod.POST)
public @ResponseBody List<List<Map<String, Object>>> sqlRun(
		@RequestParam Map<String,Object> map, 
		
		HttpSession hs
		){
	log.info("map=>{}", map.get("selectDatabase"));
	String sqlsStr = (String) map.get("sqlTa");
	String[] sqls = sqlsStr.split(";");
	List<SqlVO> sql = new ArrayList<SqlVO>();
	for (int i=0;i<sqls.length;i++) {
		SqlVO svo = new SqlVO();
		svo.setSqlTa(sqls[i]+";");
		sql.add(svo);
	}
	log.info("sql=>{}", sql);
	List<List<Map<String, Object>>> result = sqlSer.sqlRun(sql, map, hs);
	log.info("result=>{}", result);
	
//	log.info("test=>{}", (String)map.get("selectDatabase"));
////	List<Map<String, Object>> result = sqls.sqlRun(sql, hs);
//	map.put("result", result);
//
//	log.info("test=>{}", result);
	return result;
}
}*/
