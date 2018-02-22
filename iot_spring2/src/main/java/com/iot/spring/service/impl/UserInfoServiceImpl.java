package com.iot.spring.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.dao.UserInfoDAO;
import com.iot.spring.service.UserInfoService;
import com.iot.spring.vo.UserInfoVO;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDAO uidao;
	
	@Override
	public boolean login(UserInfoVO ui, Map<String, Object> rMap) {
		ui = uidao.selectUserInfo(ui);
		rMap.put("msg", "아이디 비번을 확인하세요.");
		rMap.put("biz", false);
		if(ui!=null) {
			rMap.put("msg", ui.getuName()+"님 로그인되었습니다.");
			rMap.put("biz", true);
			rMap.put("userId", ui.getuId());
			return true;
		}
		return false;
	}

	@Override
	public int insertUserInfo(UserInfoVO ui) {
		UserInfoVO cUi = new UserInfoVO();
		cUi.setuId(ui.getuId());
		if(uidao.selectUserInfo(cUi)!=null) {
			return 2;
		}
		return uidao.insertUserInfo(ui);
	}

	@Override
	public boolean checkUserInfo(UserInfoVO ui) {
		UserInfoVO cUi = new UserInfoVO();
		cUi.setuId(ui.getuId());
		if(uidao.selectUserInfo(cUi)!=null) {
			return true;
		}
		return false;
	}

}
