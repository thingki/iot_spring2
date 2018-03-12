package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class IOTSocketClientExe {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("192.168.0.34",8891);
			System.out.println(s.isConnected());
			boolean maintain = true;
			System.out.println("서버 연결 완료");
			Scanner scan = new Scanner(System.in);
			while(maintain) {
				OutputStream os = s.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
				System.out.print("할말 : " );
				String text = scan.nextLine();
				pw.println(text);
				pw.flush();
				
				InputStream is = s.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String inputStr = null;
				s.setSoTimeout(100); 
				try {
					while((inputStr=br.readLine())!=null) {
						System.out.println("수신자 왈 : " + inputStr);
					}
				}catch(Exception e) {
				}
				if(text.equals("/q")) {
					break;
				}
			}
			s.close();
			System.out.println("서버 연결 종료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
