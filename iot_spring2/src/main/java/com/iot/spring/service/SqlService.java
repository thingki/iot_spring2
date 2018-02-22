package com.iot.spring.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.iot.spring.vo.SqlVO;

public interface SqlService {
	
	public void setDatabase(Map<String, Object> dataBase, HttpSession hs);
	public List<Map<String, Object>> sqlRun(SqlVO sql, Map<String,Object> map, HttpSession hs);
	public void useDatabaseSql(SqlVO sql, Map<String,Object> map, HttpSession hs);
	public int updateSqlRun(SqlVO sql, Map<String,Object> map, HttpSession hs);
}
