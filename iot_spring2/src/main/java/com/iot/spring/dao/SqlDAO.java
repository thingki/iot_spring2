package com.iot.spring.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.iot.spring.vo.SqlVO;

public interface SqlDAO {
	public List<Map<String, Object>> sqlRun(SqlVO sql, SqlSession ss);
	public int sqlUpdate(SqlVO sql, SqlSession ss);
	public void sqlUseDatabase(SqlVO sql, SqlSession ss);
	public void setDatabase(Map<String, Object> dataBase, SqlSession ss);
}
