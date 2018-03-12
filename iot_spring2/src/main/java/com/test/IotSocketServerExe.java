package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class SocketThread extends Thread{
	private String id;
	private Socket s;
	private String msg = "조용히 합시다!!";
	BufferedReader br; 
	PrintWriter pw;
	public SocketThread(Socket s, String id){
		this.id = id;
		this.s= s;
	}
	
	public void read() throws IOException {
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	public void write(String text) throws IOException {
		pw = new PrintWriter(s.getOutputStream());
		IotSocketServerExe.send(pw, text);
	}
	public void close() throws IOException {
		synchronized(IotSocketServerExe.class) {
			br.close();
			pw.close();
			this.s.close();
			IotSocketServerExe.stMap.remove(this.id);
		}
	}
	public void run() {
		try {
			while(true) {
				read();
				String iStr = null;
				while((iStr=br.readLine())!=null) {
					if(iStr.equals("/q")) {
						close();
					}
					Log.log(iStr);
					write(this.id +":"+iStr);
				}
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
class Log{
	public static void log(Object text) {
		System.out.println(text);
	}
}
public class IotSocketServerExe {

	static Map<String,Thread> stMap = new HashMap<String,Thread>();
	public static synchronized void send(PrintWriter pw, String text) {
		pw.println(text);
		pw.flush();
	}
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8891);
			boolean maintain = true;
			
			Log.log("서버 시작!!");
			while(maintain) {
				Socket s = ss.accept();
				InetAddress ia = s.getInetAddress();
				Log.log("접속된 아이피 : " + ia);
				Thread t = new SocketThread(s,ia.toString());
				stMap.put(ia.toString(),t);
				t.start();
			}
			ss.close();
			Log.log("서버 종료!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


