package com.iot.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.dao.MenuInfoDAO;
import com.iot.spring.service.MenuInfoService;
import com.iot.spring.vo.MenuInfo;

@Service
public class MenuInfoServiceImpl implements MenuInfoService {
	@Autowired
	private MenuInfoDAO midao;
	
	@Override
	public List<MenuInfo> getMenuInfoList() {
		return midao.selectMenuInfoList();
	}

}
