package com.iot.spring.common.aspect;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.spring.dao.NaverTransDAO;
import com.iot.spring.vo.NaverMsg;

@Service
@Aspect
public class LogPrintAspect {
	@Autowired
	private NaverTransDAO ndao;
	private static final Logger logger = LoggerFactory.getLogger(LogPrintAspect.class);
	   @Before("execution(* com.iot.spring.controller.*Controller.*(..))")
	   public void beforeLog(JoinPoint jp) {
	      logger.info("@Before =>{}", jp);      
	   }
	   @Around("execution(* com.iot.spring.controller.*Controller.*(..))")
	   public Object aroundLog(ProceedingJoinPoint pjp) throws IOException {
	      logger.info("@Around begin");
	      Object obj = null;
	      long startTime = System.currentTimeMillis();
	      try {         
	         obj = pjp.proceed();         
	      } catch(Throwable e){
	         logger.error("error=>{}", e);
	         Map<String, Object> map=new HashMap<String, Object>();
				
	         ObjectMapper om = new ObjectMapper();    
	         NaverMsg nm = om.readValue(ndao.getText(e.getMessage()), NaverMsg.class);
	         map.put("errorMsg", nm.getMessage().getResult().getTranslatedText());
	         return map;
	      }
	      logger.info("@Around end, RunTime : {} ms",(System.currentTimeMillis()-startTime));      
	      return obj;      
	   }
	   @After("execution(* com.iot.spring.controller.*Controller.*(..))")
	   public void afterLog(JoinPoint jp) { 
	      logger.info("@After =>{}", jp);
	   }
}