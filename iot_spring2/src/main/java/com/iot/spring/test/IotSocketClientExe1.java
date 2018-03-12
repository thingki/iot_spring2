package com.iot.spring.test;

import java.io.*;
import java.net.*;

public class IotSocketClientExe1 {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("192.168.0.47", 8881);
			System.out.println(s.isConnected());
			boolean maintain = true;
			System.out.println("서버연결완료");
			while(maintain) {
				OutputStream os = s.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
				pw.println("어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어"
						+ "어어어어어어어어어어어어어어어어어어어어어어어어어어어어어어"
						+ "어어어어어어어어어어어어어");
			
				pw.flush();
				InputStream is = s.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String inputStr = null;
				while((inputStr=br.readLine())!=null) {
					System.out.println("서버에서 옴:" + inputStr);
				}
				pw.close();
				br.close();
				maintain = false;
			}
			s.close();
			System.out.println("서버연결종료");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
