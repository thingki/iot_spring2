package com.iot.spring.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.controller.SqlRunController;
import com.iot.spring.dao.SqlDAO;
import com.iot.spring.service.SqlService;
import com.iot.spring.vo.SqlVO;

@Service
public class SqlServiceImpl implements SqlService {
	@Autowired
	private SqlDAO sdao;
	private static final Logger log = LoggerFactory.getLogger(SqlRunController.class);
	
	@Override
	public List<Map<String, Object>> sqlRun(SqlVO sql, Map<String, Object> map, HttpSession hs) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		return sdao.sqlRun(sql, ss);
	}
	@Override
	public void useDatabaseSql(SqlVO sql, Map<String, Object> map, HttpSession hs) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		sdao.sqlUseDatabase(sql, ss);
	}
	@Override
	public int updateSqlRun(SqlVO sql, Map<String, Object> map, HttpSession hs) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		return sdao.sqlUpdate(sql, ss);
	}
	
	@Override
	public void setDatabase(Map<String, Object> dataBase, HttpSession hs) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		sdao.setDatabase(dataBase, ss);	
	}
	
	

	
	
	
//	@Override
//	public Map<String, Object> sqlRun(
//			List<SqlVO> sqlList, //sql 
//			Map<String,Object> map, //map
//			HttpSession hs //session
//			) {
//		
//		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
//		sdao.setDatabase(map, ss); //현재 선택된 데이터베이스 use 함
//		
//		
//		
//		for(SqlVO sqlStr : sqlList) {
//			if(sqlStr.getSqlTa().indexOf("use")==1) {
//				hs.removeAttribute("useDatabase"); // use 를 사용한 경우 선택된 데이터베이스값을 바꿈
//				sdao.sqlRun(sqlStr, ss); //sql실행
//			}else if(sqlStr.getSqlTa().indexOf("select")==1){
//				
//			}else{
//				if(hs.getAttribute("useDatabase")!=null) {
//					map.put("list", sdao.sqlRun(sqlStr, ss));
//				}
//				sdao.sqlRun(sqlStr, ss);
//				String str = sqlStr.getSqlTa().replace("use", "").trim();
//				str = str.replace(";", "").trim();
//				log.info("str=>{}", str);
//				hs.setAttribute("useDatabase", str);
//			}
//			msgList.add(sqlStr+"\r\n");
//		}
//		
//		
//		return map;
//	}
	//선택된 데이터베이스 이름이랑 같이 숫자도 저장하기
	

}
