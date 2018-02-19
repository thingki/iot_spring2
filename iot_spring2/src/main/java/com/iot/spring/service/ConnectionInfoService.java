package com.iot.spring.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

public interface ConnectionInfoService {
	public List<ConnectionInfoVO> getConnectionInfo(ConnectionInfoVO ci); //로그인한 아이디가 가지고 있는 커넥션 목록가져오기
	
	public List<Map<String, Object>> getDatabaseList(int ciNo, HttpSession hs) throws Exception;//커넥션 클릭하면 데이터베이스 나오기
	
	public boolean insertDbConnection(ConnectionInfoVO ci); //커넥션 추가하기!
	
	public List<TableVO> getTableList(HttpSession hs, String dbName); //테이블 리스트 받아오기
	
	public List<ColumnVO> getColumnList(HttpSession hs, Map<String, String> pMap);//테이블 정보 받아오기
	
}
