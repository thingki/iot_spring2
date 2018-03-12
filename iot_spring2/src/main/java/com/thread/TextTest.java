package com.thread;

public class TextTest extends Thread{
	String msg ="";
	String id;
	TextTest(String id, String msg){
		this.msg=msg;
		this.id=id;
	}
	public void run() {
		try {
			while(true) {
				Thread.sleep(3000);
				if(!msg.equals("")) {
					System.out.println("["+id+"] - 메세지 : "+msg);
					msg="";
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
