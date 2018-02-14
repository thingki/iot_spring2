package com.iot.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.ConnectionInfoDAO;
import com.iot.spring.vo.ConnectionInfoVO;

@Repository
public class ConnectionInfoDAOImpl implements ConnectionInfoDAO {
	
	@Autowired
	@Qualifier("dbconnectorssf")
	private SqlSessionFactory ssf;
	
	@Override
	public List<ConnectionInfoVO> selectConnectionInfo(ConnectionInfoVO ci) {
		SqlSession ss = ssf.openSession();
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
	
	@Override
	public int insertDbConnection(ConnectionInfoVO ci) {
		// TODO Auto-generated method stub
		return 0;
	}



}
