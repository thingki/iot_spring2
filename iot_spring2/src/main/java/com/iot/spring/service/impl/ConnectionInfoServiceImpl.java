package com.iot.spring.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.common.dbcon.DBConnector;
import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.service.ConnectionInfoService;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService{
	
	//private static final Logger log = LoggerFactory.getLogger(ConnectionInfoServiceImpl.class);
	
	@Autowired
	private ConnectionInfoDAO cidao;
	
	@Override
	public List<ConnectionInfoVO> getConnectionInfo(ConnectionInfoVO ci) {
		return cidao.selectConnectionInfo(ci);
	}
	//커넥션 추가하기
	@Override
	public void insertDbConnection(Map<String,Object> map, ConnectionInfoVO ci) {
		map.put("msg", "Connector Insert unsuccessful");
		if(cidao.insertDbConnection(ci)==1) {
			map.put("msg", "Connector Insert successful");
		};
	}

	@Override
	public List<Map<String, Object>> getDatabaseList(int ciNo, HttpSession hs) throws Exception {
		ConnectionInfoVO ci = cidao.selectConnection(ciNo);
		hs.setAttribute("ci", ci);
		
		
		DBConnector dbc = new DBConnector(ci);
		SqlSession ss = dbc.getSqlSession();
		hs.setAttribute("sqlSession", ss);
		List<Map<String, Object>> dbList = cidao.selectDatabaseList(ss);
		int idx = 0;
		for(Map<String,Object> dbMap : dbList) {
			dbMap.put("id", ciNo + "_" + (++idx));
			dbMap.put("text", dbMap.get("Database"));	
			dbMap.put("items", new Object[] {});
			dbMap.put("idx", idx);
		}	
		return dbList;
	}

	@Override
	public List<TableVO> getTableList(HttpSession hs, String dbName) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
	     return cidao.selectTableList(ss, dbName);
	}

	@Override
	public void getColumnList(HttpSession hs, Map<String, Object> pMap, Map<String, Object> map) {
		SqlSession ss = (SqlSession)hs.getAttribute("sqlSession");
		map.put("list", cidao.selectColumnList(ss, pMap));
		map.put("tableDatas", cidao.selectAlldataInfo(ss, pMap));
	}	
}
