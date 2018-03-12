package com.thread;

import java.util.*;

public class Excute {
	public static void main(String[] args) {
		TextTest tt = new TextTest("a", "연결성공");
		tt.start();
		String quite = "";
		Scanner scan = new Scanner(System.in);
		while(quite.equals("")) {
			quite=scan.nextLine();
			tt.msg=quite;
			if(!quite.equals("quite")) {
				quite="";
			}
		}
		System.out.println("시스템종료");
		System.exit(0);
	}
}
