package com.thread;
class ForThread extends Thread{
	String id;
	ForThread(String id){
		this.id = id;
	}
	public void run() {
		
			try {
				for(int i=0;i<1000;i++) {
				Thread.sleep(50);
				System.out.println(id + ":" +i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
	}
}
public class Excute3 {
	public static void main(String[] args) {
		ForThread ft1 = new ForThread("첫번째 포문");
		ForThread ft2 = new ForThread("두번째 포문");
		ForThread ft3 = new ForThread("세번째 포문");
		ForThread ft4 = new ForThread("네번째 포문");
		System.out.println("출발");
		ft1.start();
		ft2.start();
		ft3.start();
		ft4.start();

		System.out.println("종료");
	}
}
