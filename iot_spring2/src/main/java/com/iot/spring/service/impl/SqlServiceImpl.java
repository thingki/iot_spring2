package com.iot.spring.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.dao.SqlDAO;
import com.iot.spring.service.SqlService;
import com.iot.spring.vo.SqlVO;

@Service
public class SqlServiceImpl implements SqlService {
	@Autowired
	private SqlDAO sdao;
	
	@Override
	public List<Map<String, Object>> sqlRun(SqlVO sql, HttpSession hs) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		return sdao.sqlRun(sql, ss);
	}

}
