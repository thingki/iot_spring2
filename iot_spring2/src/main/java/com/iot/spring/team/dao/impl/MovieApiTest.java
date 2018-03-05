package com.iot.spring.team.dao.impl;

import java.io.*;
import java.net.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class MovieApiTest {
	
	public MovieApiTest() throws Exception{
        
        JSONParser jsonparser = new JSONParser();
        JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl());
        JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult");
        JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");
        for(int i = 0 ; i < array.size(); i++){
            
            JSONObject entity = (JSONObject)array.get(i);
            String movieNm = (String) entity.get("movieNm");
            System.out.println(movieNm);
        }
        
        
   }
   private static String readUrl() throws Exception {
       BufferedInputStream reader = null;
       try {
           URL url = new URL(
                   "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
                   + "searchDailyBoxOfficeList.json"
                   + "?key=[5a65d41ca88992e25db6d0c0d433a4ed]"
                   + "&targetDt=20150101");
           reader = new BufferedInputStream(url.openStream());
           StringBuffer buffer = new StringBuffer();
           int i;
           byte[] b = new byte[4096];
           while( (i = reader.read(b)) != -1){
             buffer.append(new String(b, 0, i));
           }
           return buffer.toString();
       } finally {
           if (reader != null)
               reader.close();
       }
   }

   
   public static void main(String[] args) {
       // TODO Auto-generated method stub
       try {
           new MovieApiTest();
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }
}
