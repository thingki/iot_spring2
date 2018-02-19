package com.iot.spring.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

public interface ConnectionInfoDAO {
	
	public List<ConnectionInfoVO> selectConnectionInfo(ConnectionInfoVO ci);
	
	public ConnectionInfoVO selectConnection(int ciNo);//선택한 아이디로 데이터베이스로 접속
	public List<Map<String, Object>> selectDatabaseList(SqlSession ss) throws Exception; //접속한 커넥터의 데이터베이스 보기
	
	public int insertDbConnection(ConnectionInfoVO ci); //커넥터 추가
	
	public List<TableVO> selectTableList(SqlSession ss, String dbName); //테이블 리스트를 받아옴
	
	public List<ColumnVO> selectColumnList(SqlSession ss, Map<String, String> pMap);
}
