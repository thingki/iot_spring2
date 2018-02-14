package com.iot.spring.dao;

import com.iot.spring.vo.UserInfoVO;

public interface UserInfoDAO {
	//로그인
	public UserInfoVO selectUserInfo(UserInfoVO ui);
	//회원가입
	public int insertUserInfo(UserInfoVO ui);
}
