package com.iot.spring.common.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionListener implements HttpSessionListener {
	private static final Logger log = LoggerFactory.getLogger(SessionListener.class);
	private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

	private int sessionCount = 0;

	public void sessionCreated(HttpSessionEvent event) {
		synchronized (this) {
			sessionCount++;
			HttpSession session = event.getSession();
			sessions.put(session.getId(), session);
			log.info("Session Created: " + session.getId());
			log.info("Total Sessions cnt: " + sessionCount);
			log.info("Total Sessions size: " + sessions.size());
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (this) {
			sessionCount--;
			HttpSession session = event.getSession();
			sessions.remove(session.getId());
			log.info("Session Destroyed: " + session.getId());
			log.info("Total Sessions cnt: " + sessionCount);
			log.info("Total Sessions size: " + sessions.size());
		}
	}
}