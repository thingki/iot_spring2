package com.iot.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.iot.spring.controller.SqlRunController;
import com.iot.spring.dao.SqlDAO;
import com.iot.spring.vo.SqlVO;

@Repository
public class SqlDAOImpl implements SqlDAO {

	private static final Logger log = LoggerFactory.getLogger(SqlRunController.class);
	
	@Override
	public List<Map<String, Object>> sqlRun(SqlVO sql, SqlSession ss) {
		List<Map<String, Object>> result = null;
		result=ss.selectList("sql_info.run", sql);
		return result;
	}
	@Override
	public int sqlUpdate(SqlVO sql, SqlSession ss) {
		return ss.update("sql_info.run", sql);
	}
	@Override 
	public void setDatabase(Map<String, Object> dataBase, SqlSession ss) {
		ss.selectList("sql_info.selectDatabase", dataBase);
	}
	@Override 
	public void sqlUseDatabase(SqlVO sql, SqlSession ss) {
		ss.selectList("sql_info.run", sql);
	}



}
