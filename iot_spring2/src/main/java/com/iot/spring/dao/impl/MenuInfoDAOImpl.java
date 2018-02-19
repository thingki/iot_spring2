package com.iot.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.MenuInfoDAO;
import com.iot.spring.vo.MenuInfo;

@Repository
public class MenuInfoDAOImpl implements MenuInfoDAO {
	
	@Autowired
	@Qualifier("iot2ssf")
	private SqlSessionFactory ssf;
	
	@Override
	public List<MenuInfo> selectMenuInfoList() {
		SqlSession ss = ssf.openSession();
		List<MenuInfo> mi = new ArrayList<MenuInfo>();
		mi = ss.selectList("menu_info.selectMenuInfo");
		ss.close();
		return mi;
	}

}
