package com.iot.spring.dao;

import java.util.List;

import com.iot.spring.vo.MenuInfo;

public interface MenuInfoDAO {
	public List<MenuInfo> selectMenuInfoList();
}
