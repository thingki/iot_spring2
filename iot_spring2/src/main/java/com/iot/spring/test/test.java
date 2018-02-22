package com.iot.spring.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
	
	public static void main(String[] args) {
		Map<String, Integer> myMap = new HashMap<String, Integer>();
		List<String> list = new ArrayList<String>();
		String s = "select * from User_info; select * from connection_info;";
		String[] pairs = s.split(";");
		for (int i=0;i<pairs.length;i++) {
		    list.add(pairs[i]+";");
		}
		System.out.println(list);
	}

}
