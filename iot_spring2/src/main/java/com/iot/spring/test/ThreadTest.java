package com.iot.spring.test;

import java.util.*;

public class ThreadTest implements Runnable{
	private int num;

	ThreadTest(int num){
		this.num=num;
	}
	public static void main(String[] args) {
		int h = 10;
		for(int i=1;i<h;i++) {
			Random r = new Random();
			new Thread(new ThreadTest(r.nextInt(h)+1)).start();
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			System.out.println(num+"번 마!! 달리기 시작");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Random r = new Random();
		try {
			Thread.sleep(r.nextInt(6000));
			System.out.println(num+"번 마!! 달리기 종료");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
