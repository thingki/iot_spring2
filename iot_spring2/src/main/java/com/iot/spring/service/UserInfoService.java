package com.iot.spring.service;

import java.util.Map;

import com.iot.spring.vo.UserInfoVO;

public interface UserInfoService {
	//로그인
	public boolean login(UserInfoVO ui, Map<String, Object> rMap);
	//회원가입
	public int insertUserInfo(UserInfoVO ui);
	//아이디 중복체크
	public boolean checkUserInfo(UserInfoVO ui);
}
