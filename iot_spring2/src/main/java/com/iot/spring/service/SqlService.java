package com.iot.spring.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.iot.spring.vo.SqlVO;

public interface SqlService {
	
	public List<Map<String, Object>> sqlRun(SqlVO sql, HttpSession hs);
}
