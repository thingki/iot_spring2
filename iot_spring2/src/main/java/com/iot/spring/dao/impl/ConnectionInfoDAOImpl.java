package com.iot.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.vo.ColumnVO;
import com.iot.spring.vo.ConnectionInfoVO;
import com.iot.spring.vo.TableVO;

@Repository
public class ConnectionInfoDAOImpl implements ConnectionInfoDAO {
	
	@Autowired
	@Qualifier("dbconnectorssf")
	private SqlSessionFactory ssf;
	
	@Override
	public List<ConnectionInfoVO> selectConnectionInfo(ConnectionInfoVO ci) {
		SqlSession ss = ssf.openSession();
		ss.selectList("connection_info.makeRowNum");
		List<ConnectionInfoVO> cilist = ss.selectList("connection_info.selectConncetionInfo", ci);
		ss.close();
		return cilist;
	}
	@Override
	public ConnectionInfoVO selectConnection(int ciNo) {
		SqlSession ss = ssf.openSession();
		ConnectionInfoVO ci = ss.selectOne("connection_info.selectConncetionInfoWithCiNo", ciNo);
		ss.close();
		return ci;
	}
	@Override
	public List<Map<String, Object>> selectDatabaseList(SqlSession ss) throws Exception {
		return ss.selectList("connection_info.selectDatabase");
	}
	
	//커넥션 추가하기!
	@Override
	public int insertDbConnection(ConnectionInfoVO ci) {
		SqlSession ss = ssf.openSession();
		return ss.update("connection_info.insertConncetionInfo", ci);
	}
	
	@Override
	public List<TableVO> selectTableList(SqlSession ss, String dbName) {
		List<TableVO> result = null;
		result=ss.selectList("connection_info.selectTable", dbName);
		return result;
	}
	
	@Override
	public List<ColumnVO> selectColumnList(SqlSession ss, Map<String, Object> pMap) {
		List<ColumnVO> result = ss.selectList("connection_info.selectColumn", pMap);
		return result;
	}
	@Override
	public List<Map<String, Object>> selectAlldataInfo(SqlSession ss, Map<String, Object> pMap) {
		ss.selectList("connection_info.useDatabase", pMap);
		List<Map<String, Object>> result = ss.selectList("connection_info.selectAllTableData", pMap);
		return result;
	}



}
