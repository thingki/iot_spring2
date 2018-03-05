package com.iot.spring.service.impl;

import java.util.*;

import javax.servlet.http.*;

import org.apache.ibatis.session.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.iot.spring.common.dbcon.*;
import com.iot.spring.dao.*;
import com.iot.spring.service.*;
import com.iot.spring.vo.*;

@Service
public class ConnectionInfoServiceImpl implements ConnectionInfoService{
	
	private static final Logger log = LoggerFactory.getLogger(ConnectionInfoServiceImpl.class);
	
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
	public void getDatabaseList(ConnectionInfoVO ciVo, HttpSession hs, Map<String, Object> dbMap) throws Exception {
		ConnectionInfoVO ci = cidao.selectConnection(ciVo);
		if(ci.getCiPwd().equals(ciVo.getCiPwd())) {
			hs.setAttribute("ci", ci);
			DBConnector dbc = new DBConnector(ci);
			SqlSession ss = dbc.getSqlSession();
			hs.setAttribute("sqlSession", ss);
			List<Map<String, Object>> dbList = cidao.selectDatabaseList(ss);
			int idx = 0;
			for(Map<String,Object> dbM : dbList) {
				dbM.put("id", ci.getCiNo() + "_" + (++idx));
				dbM.put("text", dbM.get("Database"));	
				dbM.put("items", new Object[] {});
				dbM.put("idx", idx);
			}	
			dbMap.put("list", dbList);
			dbMap.put("parentId", ciVo.getCiNo());
		}else {
			dbMap.put("msg", "비밀번호가 틀림");
		}
		
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
