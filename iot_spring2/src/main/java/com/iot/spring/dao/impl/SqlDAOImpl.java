package com.iot.spring.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.SqlDAO;
import com.iot.spring.vo.SqlVO;

@Repository
public class SqlDAOImpl implements SqlDAO {

	@Override
	public List<Map<String, Object>> sqlRun(SqlVO sql, SqlSession ss) {
		List<Map<String, Object>> result = null;
		Map<String, Object> map = new HashMap<String,Object>();
		ss.selectList("sql_info.selectDatabase", sql);
		result=ss.selectList("sql_info.run", sql);
		return result;
	}

}
