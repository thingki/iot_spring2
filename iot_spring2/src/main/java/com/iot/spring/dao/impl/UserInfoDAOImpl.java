package com.iot.spring.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.UserInfoDAO;
import com.iot.spring.vo.UserInfoVO;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {
	
	@Autowired
	@Qualifier("dbconnectorssf")
	private SqlSessionFactory ssf;
	
	@Override //로그인
	public UserInfoVO selectUserInfo(UserInfoVO ui) {
		SqlSession ss = ssf.openSession();
		ui = ss.selectOne("user_info.selectUserInfo", ui);
		ss.close();
		return ui;
	}

	@Override //회원가입
	public int insertUserInfo(UserInfoVO ui) {
		SqlSession ss = ssf.openSession();
		int result = 0;
		result = ss.insert("user_info.insertUserInfo", ui);
		ss.close();
		return result;
	}

}
